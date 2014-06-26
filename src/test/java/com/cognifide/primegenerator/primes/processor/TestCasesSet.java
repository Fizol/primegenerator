package com.cognifide.primegenerator.primes.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class TestCasesSet {
    
    private Collection<Case> testCases = new ArrayList<Case>();
    
    public TestCasesSet() {
        testCases.add(new Case(1, Collections.EMPTY_LIST));
        testCases.add(new Case(2, Arrays.asList(2)));
        testCases.add(new Case(3, Arrays.asList(2, 3)));
        testCases.add(new Case(10, Arrays.asList(2, 3, 5, 7)));
        testCases.add(new Case(23, 
                Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)));
        testCases.add(new Case(10, Arrays.asList(2, 3, 5, 7)));
        testCases.add(new Case(58, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 
                        29, 31, 37, 41, 43, 47, 53)));
        
    }
    
    public Collection<Case> getCases() {
        return testCases;
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
