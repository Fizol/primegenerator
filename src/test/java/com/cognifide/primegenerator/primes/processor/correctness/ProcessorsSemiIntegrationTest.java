package com.cognifide.primegenerator.primes.processor.correctness;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.CachingPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.OneCachedArrayPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.OneCachedListPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.TestCasesSet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * It's kind of state testing
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
@RunWith(Parameterized.class)
public class ProcessorsSemiIntegrationTest {

    static final PrimesCalculationAlgorithm calculationAlg = new EratosthenesPrimesAlgorithm();
    static PrimesProcessor cachingProcessor = new CachingPrimesProcessor(calculationAlg);
    static PrimesProcessor cachingArrayProcessor = new OneCachedArrayPrimesProcessor(calculationAlg);
    static PrimesProcessor cachingListProcessor = new OneCachedListPrimesProcessor(calculationAlg);

    @Parameterized.Parameters(name = "primesTo_{0}")
    public static List<Object[]> getData() {

        TestCasesSet casesSet = new TestCasesSet();
        Collection<TestCasesSet.Case> cases = casesSet.getCases();

        List<Object[]> parameters = new ArrayList<>();
        for(TestCasesSet.Case c : cases) {
            parameters.add(new Object[]{c.input, c.expectations});
        }
        return parameters;
    }

    @Parameterized.Parameter
    public int input;

    @Parameterized.Parameter(value = 1)
    public List<Integer> exs;

    @Test
    public void OneCachedArrayPrimesProcessorTest() {
        List<Integer> result = cachingArrayProcessor.generatePrimes(input);
        Assert.assertEquals(exs, result);
    }

    @Test
    public void OneCachedListPrimesProcessorTest() {
        List<Integer> result = cachingListProcessor.generatePrimes(input);
        Assert.assertEquals(exs, result);
    }

    @Test
    public void CachingPrimesProcessorTest() {
        List<Integer> result = cachingProcessor.generatePrimes(input);
        Assert.assertEquals(exs, result);
    }

}
