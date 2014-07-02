package com.cognifide.primegenerator.primes.processor.cuncrrency;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.calculation.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.primes.processor.CachingPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.OneCachedArrayPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.OneCachedListPrimesProcessor;
import com.cognifide.primegenerator.primes.processor.TestCasesSet;
import com.cognifide.primegenerator.primes.processor.TestCasesSet.Case;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
@RunWith(Parameterized.class)
public class MultithreadProcessorTest {

    static PrimesCalculationAlgorithm calculationAlg = new EratosthenesPrimesAlgorithm();

    @Parameters
    public static List<Object[]> getProcessors() {

        TestCasesSet casesSet = new TestCasesSet();

        List<Object[]> parameters = new ArrayList<>();
        parameters.add(new Object[]{new OneCachedArrayPrimesProcessor(calculationAlg), casesSet});
        parameters.add(new Object[]{new CachingPrimesProcessor(calculationAlg), casesSet});
        parameters.add(new Object[]{new OneCachedListPrimesProcessor(calculationAlg), casesSet});
        return parameters;
    }

    @Parameter
    public PrimesProcessor processor;

    @Parameter(value = 1)
    public TestCasesSet casesSet;

    @Test
    public void assertConcurrentTest() throws InterruptedException {
        List<Runnable> runnables = new ArrayList<>();
        Collection<Case> cases = casesSet.getCases();
        for (Case c : cases) {
            runnables.add(new Runnable() {
                @Override
                public void run() {
                    List<Integer> results = processor.generatePrimes(c.input);
                    assertEquals(c.expectations, results);
                }
            });
        }
        assertConcurrent(processor.getName(), runnables, 10);
    }

    /**
     * http://www.planetgeek.ch/2009/08/25/how-to-find-a-concurrency-bug-with-java/
     *
     * @param message
     * @param runnables
     * @param maxTimeoutSeconds
     * @throws InterruptedException
     */
    public static void assertConcurrent(final String message, final List<? extends Runnable> runnables, final int maxTimeoutSeconds) throws InterruptedException {
        final int numThreads = runnables.size();
        final List<Throwable> exceptions = Collections.synchronizedList(new ArrayList<Throwable>());
        final ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
        try {
            final CountDownLatch allExecutorThreadsReady = new CountDownLatch(numThreads);
            final CountDownLatch afterInitBlocker = new CountDownLatch(1);
            final CountDownLatch allDone = new CountDownLatch(numThreads);
            for (final Runnable submittedTestRunnable : runnables) {
                threadPool.submit(new Runnable() {
                    public void run() {
                        allExecutorThreadsReady.countDown();
                        try {
                            afterInitBlocker.await();
                            submittedTestRunnable.run();
                        } catch (final Throwable e) {
                            exceptions.add(e);
                        } finally {
                            allDone.countDown();
                        }
                    }
                });
            }
            // wait until all threads are ready
            assertTrue("Timeout initializing threads! Perform long lasting initializations before passing runnables to assertConcurrent", allExecutorThreadsReady.await(runnables.size() * 10, TimeUnit.MILLISECONDS));
            // start all test runners
            afterInitBlocker.countDown();
            assertTrue(message + " timeout! More than" + maxTimeoutSeconds + "seconds", allDone.await(maxTimeoutSeconds, TimeUnit.SECONDS));
        } finally {
            threadPool.shutdownNow();
        }
        assertTrue(message + "failed with exception(s)" + exceptions, exceptions.isEmpty());
    }
}
