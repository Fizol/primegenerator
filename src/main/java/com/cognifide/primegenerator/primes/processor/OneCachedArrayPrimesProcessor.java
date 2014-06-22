package com.cognifide.primegenerator.primes.processor;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import static com.google.common.base.Preconditions.checkArgument;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class OneCachedArrayPrimesProcessor implements PrimesProcessor {

    @Inject
    PrimesCalculationAlgorithm algorithm;

    OneCachedArrayPrimesProcessor() { 
    }
    
    public OneCachedArrayPrimesProcessor(PrimesCalculationAlgorithm alg) {
        this.algorithm = alg;
    }
    
    private final Object primesLock = new Object();
    private boolean[] primes = new boolean[]{};
    private int max = 0;

    //happens-before rule!
    @Override
    public List<Integer> generatePrimes(int to) {
        checkArgument(to >= 1, "Boundary number should be greater than zero!");
        synchronized (primesLock) {
            if (to <= max) {
                return toList(Arrays.copyOf(primes, to + 1));
            } else {
                //these lines should be atomic
                primes = algorithm.getPrimesArrayFromState(primes, to);
                max = to;
            }
            return toList(primes);
        }
    }

    private List<Integer> toList(boolean[] table) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (!table[i]) {
                ints.add(i);
            }
        }
        return ints;
    }
}
