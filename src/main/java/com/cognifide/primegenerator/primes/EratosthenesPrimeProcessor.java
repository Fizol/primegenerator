package com.cognifide.primegenerator.primes;

import com.cognifide.primegenerator.api.PrimeProcessor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class EratosthenesPrimeProcessor implements PrimeProcessor {

    @Override
    public List<Integer> generatePrimes(int to) {
        int n = to;
        boolean[] numbersTable = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (numbersTable[i] == true) {
                continue;
            }
            for (int j = 2 * i; j <= n; j += i) {
                numbersTable[j] = true;
            }

        }
        return toList(numbersTable);
    }

    public List<Integer> toList(boolean[] table) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i]) {
                ints.add(i);
            }
        }
        return ints;
    }
}
