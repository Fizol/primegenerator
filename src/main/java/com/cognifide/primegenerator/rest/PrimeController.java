package com.cognifide.primegenerator.rest;

import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.api.Result;
import com.google.inject.Inject;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class PrimeController {
    
    @Inject
    PrimesProcessor primeProcessor;
    
    public Result getPrimes(int to) {
        return primeProcessor.generatePrimes(to);
    }
}
