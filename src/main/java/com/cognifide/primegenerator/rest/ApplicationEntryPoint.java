package com.cognifide.primegenerator.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import spark.ResponseTransformer;
import spark.servlet.SparkApplication;
import static spark.Spark.*;

/**
 *
 * @author Maciej Pawlaczyk <pawlaczyk.mm@gmail.com>
 */
@Singleton
public class ApplicationEntryPoint implements SparkApplication {

    @Inject
    PrimeController primeController;

    @Override
    public void init() {
        
        post("/primes/generate/:to", (request, response) -> {
            return primeController.getPrimes(Integer.valueOf(request.params(":to"))).primes;
        }, new ResponseIntegerListTransformer());

        after("/primes/generate/:to", (request, response) -> {
            response.header("Content-type", "application/json");
        });
        
        exception(NumberFormatException.class, (e, request, response) -> {
            response.status(500);
            response.body("Given string is not a integer value.");
        });
        
        exception(IllegalArgumentException.class, (e, request, response) -> {
            response.status(500);
            response.body(e.getMessage());
        });
        
        exception(RuntimeException.class, (e, request, response) -> {
            response.status(500);
            response.body("Internal server error occured!");
        });
    }

    class ResponseIntegerListTransformer implements ResponseTransformer {

        private final Gson gson = new Gson();

        @Override
        public String render(Object model) throws Exception {
            return gson.toJson(model, new TypeToken<List<Integer>>() {
            }.getType());
        }
    }

}
