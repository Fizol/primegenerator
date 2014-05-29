package com.cognifide.primegenerator.api;

import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public interface PrimeProcessor {
    public List<Integer> generatePrimes(int to);
}
