package com.cognifide.primegenerator.primes;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.google.inject.Inject;
import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class OneTableCachedPrimesProcessor implements PrimesProcessor {

    @Inject
    PrimesCalculationAlgorithm algorithm;
 
    @Override
    public List<Integer> generatePrimes(int to) {
        return null;
    }
    
}
