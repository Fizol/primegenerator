package com.cognifide.primegenerator.rest;

import com.cognifide.primegenerator.api.PrimesProcessor;
import com.google.inject.Inject;
import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class PrimeController {
    
    @Inject
    PrimesProcessor primeProcessor;
    
    public List<Integer> getPrimes(int to) {
        return primeProcessor.generatePrimes(to);
    }
}
