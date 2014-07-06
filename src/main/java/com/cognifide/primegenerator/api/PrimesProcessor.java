package com.cognifide.primegenerator.api;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public interface PrimesProcessor {
    
    public String getName();
    /**
     * 
     * @param to
     * @return 
     */
    public Result generatePrimes(int to);
}
