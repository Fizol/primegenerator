package com.cognifide.primegenerator;

import com.cognifide.primegenerator.api.PrimesCalculationAlgorithm;
import com.cognifide.primegenerator.api.PrimesProcessor;
import com.cognifide.primegenerator.primes.CachingPrimesProcessor;
import com.cognifide.primegenerator.primes.EratosthenesPrimesAlgorithm;
import com.cognifide.primegenerator.rest.ApplicationEntryPoint;
import com.cognifide.primegenerator.rest.PrimeController;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {

                Map<String, String> params = new HashMap<>();
                params.put("applicationClass", ApplicationEntryPoint.class.getName());
                
                bind(PrimesCalculationAlgorithm.class).to(EratosthenesPrimesAlgorithm.class);
                bind(PrimesProcessor.class).to(CachingPrimesProcessor.class);
                bind(PrimeController.class).toInstance(new PrimeController());
                bind(spark.servlet.SparkApplication.class).to(ApplicationEntryPoint.class);
                filter("/*").through(GuicySparkFilter.class);
            }
        });
    }

}
