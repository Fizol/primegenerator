package com.cognifide.primegenerator.primes.performence;

import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.CachingPrimesProcessor;
import java.util.Random;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * No asserts needed. Only to see elapsed time.
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
@Category(PerformenceCategory.class)
public class CachingPerformenceTest {

    static final int N = 10000;
    static final int BOUND = 10000000;
    static int[] RANDOMS;

    @BeforeClass
    public static void setUpClass() {
        //let's exam 10000 random numbers from 1 to 10,000,000
        Random rnd = new Random();
        RANDOMS = new int[N + 1];
        for (int i = 0; i < RANDOMS.length; i++) {
            RANDOMS[i] = 1 + rnd.nextInt(BOUND + 1);
        }
    }

    @Test
    public void cacheProcessor() {
        CachingPrimesProcessor processor
                = new CachingPrimesProcessor(new EratosthenesPrimesAlgorithm());
        for (int i = 0; i < RANDOMS.length; i++) {
            processor.generatePrimes(RANDOMS[i]);
        }
    }
}
