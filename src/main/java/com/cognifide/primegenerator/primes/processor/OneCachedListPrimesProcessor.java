package com.cognifide.primegenerator.primes.processor;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import static com.google.common.base.Preconditions.checkArgument;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class OneCachedListPrimesProcessor implements PrimesProcessor {

    @Inject
    PrimesCalculationAlgorithm algorithm;

    private final List<Integer> cachedPrimes = new ArrayList<>();
    private final Object lock = new Object();
    private int max = 0;

    //for tests
    public OneCachedListPrimesProcessor(PrimesCalculationAlgorithm alg) {
        this.algorithm = alg;
    }

    @Override
    public String getName() {
        return OneCachedListPrimesProcessor.class.getCanonicalName();
    }

    //don't return references. Maybe return immutable view, or stream?
    @Override
    public List<Integer> generatePrimes(int to) {
        //synchronize on update
        checkArgument(to >= 1, "Boundary number should be greater than zero!");
        if (to <= max) {
            return rewritePrimesCache(to);
        } else {
            synchronized (lock) {
                boolean[] primesArray = algorithm.getPrimesArray(to);
                ListIterator<Integer> cachedIterator = cachedPrimes.listIterator(cachedPrimes.size());
                //maybe it's better add batches than single elements
                for (int index = max + 1; index <= to; index++) {
                    if (!primesArray[index]) {
                        cachedIterator.add(index);
                    }
                }
                max = to;

                List<Integer> primes = new ArrayList<>(cachedPrimes);
                return primes;
            }
        }
    }

    private List<Integer> rewritePrimesCache(int to) {
        List<Integer> primes = new ArrayList<>();
        for (Integer i : cachedPrimes) {
            if (i > to) {
                break;
            }
            primes.add(i);
        }
        return primes;
    }
}
