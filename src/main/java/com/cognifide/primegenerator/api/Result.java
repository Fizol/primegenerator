package com.cognifide.primegenerator.api;

import java.util.List;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class Result {
    public List<Integer> primes;
    //for debbugg
    public String name;
    
    public Result(List<Integer> primes) {
        this.primes = primes;
    }

    public Result(List<Integer> primes, String name) {
        this.primes = primes;
        this.name = name;
    }
}
