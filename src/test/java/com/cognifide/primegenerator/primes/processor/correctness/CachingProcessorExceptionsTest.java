package com.cognifide.primegenerator.primes.processor.correctness;

import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.CachingPrimesProcessor;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class CachingProcessorExceptionsTest {

    static PrimesProcessor primes;

    @BeforeClass
    public static void setUpClass() {
        CachingPrimesProcessor cachingPrimesProcessor
                = new CachingPrimesProcessor(new EratosthenesPrimesAlgorithm());
        primes = cachingPrimesProcessor;
    }

    @Test(expected = IllegalArgumentException.class)
    public void primesTo_negative() {
        List<Integer> exPrimes = primes.generatePrimes(-1);
        Assert.assertEquals(Collections.EMPTY_LIST, exPrimes);
    }
}
