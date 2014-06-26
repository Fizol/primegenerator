package com.cognifide.primegenerator.primes.processor.correctness;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.CachingPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.TestCasesSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
@RunWith(Parameterized.class)
public class CachingProcessorTest {
    
    static PrimesCalculationAlgorithm calculationAlg = new EratosthenesPrimesAlgorithm();
    static PrimesProcessor processor = new CachingPrimesProcessor(calculationAlg);
    
    @Parameters(name = "primesTo_{0}")
    public static List<Object[]> getProcessors() {
        
        TestCasesSet casesSet = new TestCasesSet();
        Collection<TestCasesSet.Case> cases = casesSet.getCases();
        
        List<Object[]> parameters = new ArrayList<>();
        for(TestCasesSet.Case c : cases) {
            parameters.add(new Object[]{c.input, c.expectations});
        }
        return parameters;
    }
    
    @Parameter
    public int input;
    
    @Parameter(value = 1)
    public List<Integer> exs;
    
    @Test
    public void runTest() {
        List<Integer> result = processor.generatePrimes(input);
        Assert.assertEquals(exs, result);
    }
}
