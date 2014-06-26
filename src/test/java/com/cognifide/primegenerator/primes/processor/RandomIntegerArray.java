package com.cognifide.primegenerator.primes.processor;

import java.util.Random;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class RandomIntegerArray {

    private final int N;
    private final int BOUND;
    private final int[] RANDOMS;

    public RandomIntegerArray(int n, int bound) {
        N = n;
        BOUND = bound;
        Random rnd = new Random();
        RANDOMS = new int[N + 1];
        for (int i = 0; i < RANDOMS.length; i++) {
            RANDOMS[i] = 1 + rnd.nextInt(BOUND + 1);
        }
    }

    public int[] getRandoms() {
        return RANDOMS;
    }
}
