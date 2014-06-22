package com.cognifide.primegenerator.primes.processor;

import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class OneCachedArrayProcessorTest {
    static PrimesProcessor primes;

    @BeforeClass
    public static void setUpClass() {
        OneCachedArrayPrimesProcessor oneCachedArray
                = new OneCachedArrayPrimesProcessor();
        oneCachedArray.algorithm = new EratosthenesPrimesAlgorithm();
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

    @Test
    public void primesTo_1() {
        List<Integer> exPrimes = primes.generatePrimes(1);
        Assert.assertEquals(Collections.EMPTY_LIST, exPrimes);
    }

    @Test
    public void primesTo_2() {
        List<Integer> exPrimes = primes.generatePrimes(2);
        Assert.assertEquals(Arrays.asList(2), exPrimes);
    }

    @Test
    public void primesTo_3() {
        List<Integer> exPrimes = primes.generatePrimes(3);
        Assert.assertEquals(Arrays.asList(2, 3), exPrimes);
    }

    @Test
    public void primesTo_10() {
        List<Integer> exPrimes = primes.generatePrimes(10);
        Assert.assertEquals(Arrays.asList(2, 3, 5, 7), exPrimes);
    }

    @Test
    public void primesTo_23() {
        List<Integer> exPrimes = primes.generatePrimes(23);
        Assert.assertEquals(
                Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23), exPrimes);
    }

    @Test
    public void primesTo_58() {
        List<Integer> exPrimes = primes.generatePrimes(58);
        Assert.assertEquals(
                Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 
                        29, 31, 37, 41, 43, 47, 53), exPrimes);
    }
}
