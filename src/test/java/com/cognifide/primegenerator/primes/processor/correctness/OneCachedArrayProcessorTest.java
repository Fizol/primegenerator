/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cognifide.primegenerator.primes.processor.correctness;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.OneCachedArrayPrimesProcessor;
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
public class OneCachedArrayProcessorTest {
    
    static PrimesCalculationAlgorithm calculationAlg = new EratosthenesPrimesAlgorithm();
    static PrimesProcessor processor = new OneCachedArrayPrimesProcessor(calculationAlg);
    
    @Parameters(name = "primesTo_{0}")
    public static List<Object[]> getProcessors() {
        
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
    public void runTest() {
        List<Integer> result = processor.generatePrimes(input);
        Assert.assertEquals(exs, result);
    }
}
