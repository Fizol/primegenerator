package com.cognifide.primegenerator.primes.processor.correctness;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.api.Result;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.CachingPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.OneCachedArrayPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.OneCachedListPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.TestCasesSet;
import com.cognifide.primegenerator.primes.processor.TestCasesSet.Case;
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
public class ProcessorsTest {
    
    static PrimesCalculationAlgorithm calculationAlg = new EratosthenesPrimesAlgorithm();
    
    @Parameters(name = "primesTo_{0}")
    public static List<Object[]> getData() {
        
        TestCasesSet casesSet = new TestCasesSet();
        Collection<Case> cases = casesSet.getCases();
        
        List<Object[]> parameters = new ArrayList<>();
        for(Case c : cases) {
            parameters.add(new Object[]{c.input, c.expectations});
        }
        return parameters;
    }
    
    @Parameter
    public int input;
    
    @Parameter(value = 1)
    public List<Integer> exs;
    
    @Test
    public void OneCachedArrayPrimesProcessorTest() {
        PrimesProcessor processor = new OneCachedArrayPrimesProcessor(calculationAlg);
        Result result = processor.generatePrimes(input);
        Assert.assertEquals(exs, result.primes);
    }
    
    @Test
    public void OneCachedListPrimesProcessorTest() {
        PrimesProcessor processor = new OneCachedListPrimesProcessor(calculationAlg);
        Result result = processor.generatePrimes(input);
        Assert.assertEquals(exs, result.primes);
    }
    
    @Test
    public void CachingPrimesProcessorTest() {
        PrimesProcessor processor = new CachingPrimesProcessor(calculationAlg);
        Result result = processor.generatePrimes(input);
        Assert.assertEquals(exs, result.primes);
    }
}
