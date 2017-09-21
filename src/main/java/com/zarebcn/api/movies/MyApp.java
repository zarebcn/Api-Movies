package com.zarebcn.api.movies;

import com.zarebcn.api.movies.controller.MovieApi;
import com.zarebcn.api.movies.controller.RentalApi;
import com.zarebcn.api.movies.service.MovieService;
import com.zarebcn.api.movies.service.RentalService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * To start server, run with args: server
 */
public class MyApp extends Application<MyAppConfig> {

    public static void main(String[] args) throws Exception {
        new MyApp().run(args);
    }

    @Override
    public String getName() {
        return "my-app";
    }

    @Override
    public void initialize(Bootstrap<MyAppConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/frontend/", "/", "movies.html"));
    }

    @Override
    public void run(MyAppConfig config, Environment env) throws Exception {

        MovieService movieService = new MovieService();
        RentalService rentalService = new RentalService();

        env.jersey().register(new MovieApi(movieService));
        env.jersey().register(new RentalApi(rentalService));
    }
}

