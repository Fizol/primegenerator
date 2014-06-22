package com.cognifide.primegenerator.api;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public interface PrimesCalculationAlgorithm {

    /**
     * 
     * @param to must be >= than one. Otherwise throws IllegalArgumentException
     * @return booleans array that contains indexes in [0, to] range, and primes
     * are indexes under false values.
     */
    boolean[] getPrimesArray(int to);
    
    /**
     * 
     * @param state
     * @param to
     * @return 
     */
    boolean[] getPrimesArrayFromState(boolean[] state, int to);
}
