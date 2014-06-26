package com.cognifide.primegenerator.primes.processor.performence;

import com.cognifide.primegenerator.primes.processor.RandomIntegerArray;
import com.cognifide.primegenerator.primes.processor.PerformenceCategory;
import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.CachingPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.OneCachedArrayPrimesProcessor;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
@RunWith(Parameterized.class)
@Category(PerformenceCategory.class)
public class ProcessorsImplPerformenceTest {

    static PrimesCalculationAlgorithm calculationAlg = new EratosthenesPrimesAlgorithm();
    static RandomIntegerArray randomArray = new RandomIntegerArray(100000, 1000000);

    @Parameters
    public static List<Object[]> getProcessors() {
        return Arrays.asList(new Object[][]{
            {new OneCachedArrayPrimesProcessor(calculationAlg), randomArray.getRandoms()},
            {new CachingPrimesProcessor(calculationAlg), randomArray.getRandoms()}
        });
    }

    @Parameter
    public PrimesProcessor processor;

    @Parameter(value = 1)
    public int[] randoms;

    @Test
    public void runTest() {
        long start = System.nanoTime();
        for (int r : randoms) {
            processor.generatePrimes(r);
        }
        long stop = System.nanoTime() - start;
        System.out.println(processor.getName() + ": "
                + TimeUnit.MILLISECONDS.convert(stop, TimeUnit.NANOSECONDS) + "ms");
    }
}
