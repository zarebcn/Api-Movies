package com.zarebcn.api.movies;

import com.zarebcn.api.movies.controller.MovieApi;
import com.zarebcn.api.movies.controller.RentalApi;
import com.zarebcn.api.movies.database.MovieDao;
import com.zarebcn.api.movies.database.MovieDaoSpringJdbc;
import com.zarebcn.api.movies.service.MovieService;
import com.zarebcn.api.movies.service.RentalService;
import com.zarebcn.api.movies.service.UserService;
import com.zarebcn.api.movies.util.DbUtil;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.sql.DataSource;
import java.util.EnumSet;

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

        //nuevo
        DataSource ds = DbUtil.getDataSource();
        MovieDao movieDao = new MovieDaoSpringJdbc(ds);
        MovieService movieService = new MovieService(movieDao);
        //

        UserService userService = new UserService();
        //MovieServiceOld movieService = new MovieServiceOld();
        RentalService rentalService = new RentalService(userService, movieService);

        env.jersey().register(new MovieApi(movieService));
        env.jersey().register(new RentalApi(rentalService));

        setupCors(env);
    }

    private void setupCors(Environment env)
    {
        final FilterRegistration.Dynamic cors = env.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
    }
}

