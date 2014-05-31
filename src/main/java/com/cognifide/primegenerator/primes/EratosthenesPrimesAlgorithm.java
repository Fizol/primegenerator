package com.cognifide.primegenerator.primes;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class EratosthenesPrimesAlgorithm implements PrimesCalculationAlgorithm {

    /**
     * Simple implementation copied from wikipedia.
     *
     * @param to
     * @return
     */
    @Override
    public boolean[] getPrimesArray(int to) {
        checkArgument(to >= 1, "Bound number should be greater than zero!");
        int n = to;
        boolean[] numbersTable = new boolean[n + 1];
        numbersTable[0] = true;
        numbersTable[1] = true;
        for (int i = 2; i * i <= n; i++) {
            if (numbersTable[i]) {
                continue;
            }
            for (int j = 2 * i; j <= n; j += i) {
                numbersTable[j] = true;
            }
        }
        return numbersTable;
    }

}
