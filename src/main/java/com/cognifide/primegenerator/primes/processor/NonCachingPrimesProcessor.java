package com.cognifide.primegenerator.primes.processor;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class NonCachingPrimesProcessor implements PrimesProcessor {

    @Inject
    PrimesCalculationAlgorithm algorithm;
    
    //for tests
    public NonCachingPrimesProcessor(PrimesCalculationAlgorithm alg) {
        this.algorithm = alg;
    }
    
    @Override
    public String getName() {
        return NonCachingPrimesProcessor.class.getCanonicalName();
    }
    
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
