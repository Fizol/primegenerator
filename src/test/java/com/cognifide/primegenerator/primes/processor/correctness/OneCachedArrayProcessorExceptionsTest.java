/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cognifide.primegenerator.primes.processor.correctness;

import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.OneCachedArrayPrimesProcessor;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class OneCachedArrayProcessorExceptionsTest {
    static PrimesProcessor primes;

    @BeforeClass
    public static void setUpClass() {
        OneCachedArrayPrimesProcessor oneCachedArray
                = new OneCachedArrayPrimesProcessor(new EratosthenesPrimesAlgorithm());
        primes = oneCachedArray;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void primesTo_negative() {
        List<Integer> exPrimes = primes.generatePrimes(-1);
        Assert.assertEquals(Collections.EMPTY_LIST, exPrimes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void primesTo_0() {
        List<Integer> exPrimes = primes.generatePrimes(0);
        Assert.assertEquals(Collections.EMPTY_LIST, exPrimes);
    }
}
