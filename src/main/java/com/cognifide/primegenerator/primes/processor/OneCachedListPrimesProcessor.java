package com.cognifide.primegenerator.primes.processor;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.api.Result;
import static com.google.common.base.Preconditions.checkArgument;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class OneCachedListPrimesProcessor implements PrimesProcessor {

    @Inject
    PrimesCalculationAlgorithm algorithm;

    private List<Integer> cachedPrimes = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int max = 0;
    private volatile boolean isWrite = false;

    //for tests
    public OneCachedListPrimesProcessor(PrimesCalculationAlgorithm alg) {
        this.algorithm = alg;
    }

    @Override
    public String getName() {
        return OneCachedListPrimesProcessor.class.getCanonicalName();
    }

    //don't return references. Maybe return immutable view, or stream?
    @Override
    public Result generatePrimes(int to) {
        //synchronize on update
        checkArgument(to >= 1, "Boundary number should be greater than zero!");
        lock.writeLock().lock();
        if (to <= max) {
            lock.writeLock().unlock();
            lock.readLock().lock();
            Result res = new Result(Collections.EMPTY_LIST);
            try {
                res = rewritePrimesCache(to);
            } finally {
                lock.readLock().unlock();
            }
            return res;
        } else {
            List<Integer> primes = new ArrayList<>();
            try {
                boolean[] primesArray = algorithm.getPrimesArray(to);
                ListIterator<Integer> cachedIterator = cachedPrimes.listIterator(cachedPrimes.size());
                //maybe it's better add batches than single elements
                for (int index = max + 1; index <= to; index++) {
                    if (!primesArray[index]) {
                        cachedIterator.add(index);
                    }
                }
                max = to;
                primes.addAll(cachedPrimes);
            } finally {
                lock.writeLock().unlock();
            }
            return new Result(primes, "awsome");
        }
    }
    //read write lock
    //read while updating

    private Result rewritePrimesCache(int to) {
        //copy for thread safe
        List<Integer> primes = new ArrayList<>();
        int size = cachedPrimes.size();
        for (int i = 0; i < size; i++) {
            Integer prime = cachedPrimes.get(i);
            if (prime > to) {
                break;
            }
            primes.add(prime);
        }
        return new Result(primes, "rewrite");
    }
}
