package com.cognifide.primegenerator.primes;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class CachingPrimesProcessor implements PrimesProcessor {
    
    @Inject
    PrimesCalculationAlgorithm algorithm;
    
//    LoadingCache<Integer, Integer> graphs = CacheBuilder.newBuilder()
//       .maximumSize(1000)
//       .expireAfterWrite(10, TimeUnit.MINUTES)
//       .removalListener(MY_LISTENER)
//       .build(
//           new CacheLoader<Integer, Integer>() {
//             public Integer load(Integer key) {
//               return createExpensiveGraph(key);
//             }
//           });
    
    @Override
    public List<Integer> generatePrimes(int to) {
        return toList(algorithm.getPrimesArray(to));
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
}
