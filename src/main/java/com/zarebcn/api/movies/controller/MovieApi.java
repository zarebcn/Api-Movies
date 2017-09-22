package com.zarebcn.api.movies.controller;

import com.zarebcn.api.movies.model.Movie;
import com.zarebcn.api.movies.service.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieApi {

    private MovieService movieService;

    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }


    @GET
    public Collection<Movie> viewMovies(@QueryParam("search") String search) {

        return movieService.findByTitle(search);
    }

    @GET
    @Path("{id}")
    public Movie viewMovie(@PathParam("id") int id) {

        return movieService.getById(id);
    }

    @POST
    public Movie addMovie(Movie movie) {

        System.out.println("Received book: " + movie);

        return movieService.addMovie(movie);
    }

    @DELETE
    @Path("{id}")
    public void deleteMovie(@PathParam("id") int id) {

        movieService.deleteMovie(id);
    }

    @PUT
    @Path("{id}")
    public Movie editMovie(@PathParam("id") int id, Movie movie) {

        return movieService.editMovie(id, movie);
    }

    @PUT
    @Path("/rented/{id}")
    public Movie setRented(@PathParam("id") int id) {

        return movieService.setRented(id);
    }

    @PUT
    @Path("/available/{id}")
    public Movie setAvailable(@PathParam("id") int id) {

        return movieService.setAvailable(id);
    }

}

