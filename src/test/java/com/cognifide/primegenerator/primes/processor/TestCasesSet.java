package com.cognifide.primegenerator.primes.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class TestCasesSet {

    private final Collection<Case> testCases = new ArrayList<>();

    public TestCasesSet() {
        testCases.add(new Case(1, Collections.EMPTY_LIST));
        testCases.add(new Case(2, Arrays.asList(2)));
        testCases.add(new Case(3, Arrays.asList(2, 3)));
        testCases.add(new Case(10, Arrays.asList(2, 3, 5, 7)));
        testCases.add(new Case(23,
                Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)));
        testCases.add(new Case(30, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23,
                29)));
        testCases.add(new Case(40, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23,
                29, 31, 37)));
        testCases.add(new Case(58, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23,
                29, 31, 37, 41, 43, 47, 53)));
        testCases.add(new Case(59, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23,
                29, 31, 37, 41, 43, 47, 53, 59)));
    }

    public Collection<Case> getCases() {
        return testCases;
    }

    public Collection<Case> getRandomSet(int number) {
        Collection<Case> cases = new ArrayList<>();
        Random rnd = new Random();
        for(int i = 0; i < number; i++) {               
            int nextInt = rnd.nextInt(truePrimes.length);
            cases.add(new Case(truePrimes[nextInt], rewrite(nextInt)));
        }
        return cases;
    }

    private Integer[] truePrimes = {
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
        31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
        73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
        127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
        179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
        233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
        283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
        353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
        419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
        467, 479, 487, 491, 499, 503, 509, 521, 523, 541,
        547, 557, 563, 569, 571, 577, 587, 593, 599, 601
    };

    private List<Integer> rewrite(int nextInt) {
        return Arrays.asList(Arrays.copyOf(truePrimes, nextInt + 1));
    }

    public class Case {

        public int input;
        public List<Integer> expectations;

        public Case(int input, List<Integer> expectations) {
            this.input = input;
            this.expectations = expectations;
        }
    }
}
