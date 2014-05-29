package com.cognifide.primegenerator;

import com.cognifide.primegenerator.api.PrimeProcessor;
import com.cognifide.primegenerator.primes.EratosthenesPrimeProcessor;
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
                
                bind(PrimeProcessor.class).to(EratosthenesPrimeProcessor.class);
                bind(PrimeController.class).toInstance(new PrimeController());
                bind(spark.servlet.SparkApplication.class).to(ApplicationEntryPoint.class);
                filter("/*").through(GuicySparkFilter.class);
            }
        });
    }

}
