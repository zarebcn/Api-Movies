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

        //nuevo
        if (search != null) {
            return movieService.findByTitle(search);
        } else {
            return movieService.getAll();
        }

        //return movieService.findByTitle(search);
    }

    @GET
    @Path("{id}")
    public Movie viewMovie(@PathParam("id") int id) {

        return movieService.getById(id);
    }

    @POST
    public Movie addMovie(Movie movie) {

        System.out.println("Received book: " + movie);

        return movieService.add(movie);
    }

    @DELETE
    @Path("{id}")
    public String deleteMovie(@PathParam("id") int id) {

       return movieService.delete(id);
    }

    @PUT
    @Path("{id}")
    public String editMovie(@PathParam("id") int id, Movie movie) {

        return movieService.edit(id, movie);
    }
}

