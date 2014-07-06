package com.cognifide.primegenerator.primes.processor;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.api.Result;
import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;

/**
 * Use caching to do less computing with algorithm. This strategy costs huge
 * amount of memory.
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class CachingPrimesProcessor implements PrimesProcessor {

    @Inject
    PrimesCalculationAlgorithm algorithm;

    CachingPrimesProcessor() {
    }

    //for tests
    public CachingPrimesProcessor(PrimesCalculationAlgorithm alg) {
        this.algorithm = alg;
    }

    // storage for cached keys
    private final ConcurrentSkipListSet<Integer> cachedKeys = new ConcurrentSkipListSet<>();
    private final LoadingCache<Integer, boolean[]> primesCache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .removalListener(new PrimesRemovalListener())
            .build(new PrimesCacheLoader());

    @Override
    public String getName() {
        return CachingPrimesProcessor.class.getCanonicalName();
    }

    @Override
    public Result generatePrimes(int to) {
        try {
            checkArgument(to >= 1, "Boundary number should be greater than zero!");
            return new Result(toList(primesCache.get(to)));
        } catch (ExecutionException ex) {
            //logger logs entry
            throw new RuntimeException();
        }
    }

    private List<Integer> toList(boolean[] table) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (!table[i]) {
                ints.add(i);
            }
        }
        return ints;
    }

    private class PrimesCacheLoader extends CacheLoader<Integer, boolean[]> {

        /**
         * To avoid processing every time, we try to rewrite lower values of
         * known bound.
         *
         * @param key
         * @return
         * @throws Exception
         */
        @Override
        public boolean[] load(Integer key) throws Exception {
            Integer ceiling = cachedKeys.ceiling(key);
            boolean[] result;
            if (ceiling != null) {
                result = rewrite(primesCache.get(ceiling), key);
            } else {
                result = algorithm.getPrimesArray(key);
            }
            cachedKeys.add(key);
            return result;
        }

        private boolean[] rewrite(boolean[] cached, int key) {
            return Arrays.copyOf(cached, key + 1);
        }
    }

    private class PrimesRemovalListener implements RemovalListener<Integer, boolean[]> {

        @Override
        public void onRemoval(RemovalNotification<Integer, boolean[]> rn) {
            cachedKeys.remove(rn.getKey());
        }

    }
}
