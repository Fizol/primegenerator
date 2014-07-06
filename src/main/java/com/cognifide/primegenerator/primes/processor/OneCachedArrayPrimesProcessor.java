package com.cognifide.primegenerator.primes.processor;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.api.Result;
import static com.google.common.base.Preconditions.checkArgument;
import com.google.inject.Inject;
import java.util.ArrayList;
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
    private boolean[] cachedPrimes = new boolean[]{};
    private int max = 0;

    @Override
    public String getName() {
        return OneCachedArrayPrimesProcessor.class.getCanonicalName();
    }
    
    //happens-before rule!
    @Override
    public Result generatePrimes(int to) {
        checkArgument(to >= 1, "Boundary number should be greater than zero!");
        synchronized (primesLock) {
            if (to <= max) {
                return new Result(rewrite(to));
            } else {
                //these lines should be atomic
                cachedPrimes = algorithm.getPrimesArrayFromState(cachedPrimes, to);
                max = to;
            }
            return new Result(rewrite(max));
        }
    }
    
    private List<Integer> rewrite(int to) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i <= to; i++) {
            if (!cachedPrimes[i]) {
                ints.add(i);
            }
        }
        return ints;
    }
}
