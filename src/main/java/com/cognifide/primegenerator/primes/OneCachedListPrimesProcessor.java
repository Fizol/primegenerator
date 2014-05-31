package com.cognifide.primegenerator.primes;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class OneCachedListPrimesProcessor implements PrimesProcessor {

    @Inject
    PrimesCalculationAlgorithm algorithm;
    
    private final List<Integer> primes = Collections.synchronizedList(new LinkedList<Integer>());
    private final AtomicLong max = new AtomicLong(0);
    
    @Override
    public List<Integer> generatePrimes(int to) {
        throw new UnsupportedOperationException();
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
