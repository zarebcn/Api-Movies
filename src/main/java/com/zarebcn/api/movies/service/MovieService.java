package com.zarebcn.api.movies.service;

import com.zarebcn.api.movies.database.MovieDao;
import com.zarebcn.api.movies.model.Movie;

import java.util.Collection;

public class MovieService {

    private MovieDao movieDao;

    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }


    /** Returns a book by id, or null if not found. */
    public Movie getById(int id) {

        return movieDao.getById(id);
    }

    public Collection<Movie> getAll() {

        return movieDao.getAll();
    }

    public Collection<Movie> findByTitle(String search) {

        return movieDao.findByTitle(search);
    }

    public Movie add(Movie movie) {

        return movieDao.addMovie(movie);
    }

    public String edit(int id, Movie movie) {

        return movieDao.editMovie(id, movie);
    }

    public void rentMovie(int id) {

        movieDao.rentMovie(id);
    }

    public void returnMovie(int id) {

        movieDao.returnMovie(id);
    }

    public String delete(int id) {

       return movieDao.deleteMovie(id);
    }
}
