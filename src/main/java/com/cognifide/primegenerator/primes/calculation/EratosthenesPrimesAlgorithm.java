package com.cognifide.primegenerator.primes.calculation;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import static com.google.common.base.Preconditions.checkArgument;
import java.util.Arrays;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class EratosthenesPrimesAlgorithm implements PrimesCalculationAlgorithm {

    //what about make class for result, raw boolean? is it good?
    /**
     * Simple implementation copied from wikipedia.
     *
     * @param to
     * @return
     */
    @Override
    public boolean[] getPrimesArray(int to) {
        return getPrimesArrayFromState(new boolean[]{}, to);
    }

    //leave only this method, and make class for state
    @Override
    public boolean[] getPrimesArrayFromState(boolean[] state, int to) {
        checkArgument(to >= 1, "Boundary number should be greater or equal zero!");
        return getPrimesArray_(state, to);
    }
    
    //no state implemented yet
    private boolean[] getPrimesArray_(boolean[] state, int to) {
        int n = to;
//        int stateLength = state.length;
        boolean[] numbersTable = Arrays.copyOf(state, n + 1);
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
