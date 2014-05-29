package com.cognifide.primegenerator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javax.servlet.FilterConfig;
import spark.servlet.SparkApplication;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
@Singleton
public class GuicySparkFilter extends spark.servlet.SparkFilter {
    
    @Inject
    SparkApplication application;
    
    @Override
    protected SparkApplication getApplication(FilterConfig filterConfig) {
        return application;
    }
}
