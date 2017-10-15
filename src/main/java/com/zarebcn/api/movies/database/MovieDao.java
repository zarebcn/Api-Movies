package com.zarebcn.api.movies.database;

import com.zarebcn.api.movies.model.Movie;

import java.util.Collection;

public interface MovieDao {

    Movie getById(int id);

    Collection<Movie> getAll();

    Collection<Movie> findByTitle(String search);

    Movie addMovie(Movie movie);

    String deleteMovie(int id);

    String editMovie(int id, Movie movie);

    void rentMovie(int id);

    void returnMovie(int id);

}
