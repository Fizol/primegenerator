package com.cognifide.primegenerator.primes.calculation;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class PrimesAlgorithmCommonTest {

    PrimesCalculationAlgorithm algorithm = new EratosthenesPrimesAlgorithm();

    @Test(expected = IllegalArgumentException.class)
    public void primesTo_negative() {
        boolean[] primesArray = algorithm.getPrimesArray(-1);
        Assert.assertEquals(Collections.EMPTY_LIST, toList(primesArray));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void primesTo_0() {
        boolean[] primesArray = algorithm.getPrimesArray(0);
        Assert.assertEquals(Collections.EMPTY_LIST, toList(primesArray));
    }

    @Test
    public void primesTo_1() {
        boolean[] primesArray = algorithm.getPrimesArray(1);
        Assert.assertEquals(Collections.EMPTY_LIST, toList(primesArray));
    }

    @Test
    public void primesTo_2() {
        boolean[] primesArray = algorithm.getPrimesArray(2);
        Assert.assertEquals(Arrays.asList(2), toList(primesArray));
    }

    @Test
    public void primesTo_3() {
        boolean[] primesArray = algorithm.getPrimesArray(3);
        Assert.assertEquals(Arrays.asList(2, 3), toList(primesArray));
    }

    @Test
    public void primesTo_10() {
        boolean[] primesArray = algorithm.getPrimesArray(10);
        Assert.assertEquals(Arrays.asList(2, 3, 5, 7), toList(primesArray));
    }

    @Test
    public void primesTo_23() {
        boolean[] primesArray = algorithm.getPrimesArray(23);
        Assert.assertEquals(
                Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23), toList(primesArray));
    }

    //needed to transform raw results from algorithm
    public List<Integer> toList(boolean[] table) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (!table[i]) {
                ints.add(i);
            }
        }
        return ints;
    }
}
