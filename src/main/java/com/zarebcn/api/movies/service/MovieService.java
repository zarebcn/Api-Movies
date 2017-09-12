package com.zarebcn.api.movies.service;

import com.zarebcn.api.movies.model.Movie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService {

    private Map<Integer, Movie> movies;
    private int nextId;

    public MovieService() {

        movies = new HashMap<>();
        movies.put(1,  new Movie(1,  "Head first Java", "Kathy Sierra, Bert Bates", 720) );
        movies.put(2,  new Movie(2,  "Refactoring", "Martin Fowler", 464) );
        movies.put(3,  new Movie(3,  "Head first design patterns", "Eric Freeman, Beth Robson", 694) );
        movies.put(4,  new Movie(4,  "Clean code", "Robert C. Martin", 288) );

        nextId = 5;
    }

    public Movie getById(int id) {

        return movies.get(id);
    }

    public Collection<Movie> findByTitle(String search) {

        final Collection<Movie> result;

        if (search != null) {
            result = this.movies.values().stream()
                    .filter(movie -> movie.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            result = this.movies.values();
        }

        return result;
    }

    public Movie addMovie(Movie movie) {

        movie.setId(nextId);

        movies.put(nextId, movie);

        nextId++;

        return movie;
    }


    public void deleteMovie(int id) {

        movies.remove(id);
    }

    public Movie editMovie(int id, Movie movie) {

        movie.setTitle(movie.getTitle());
        movie.setDirector(movie.getDirector());
        movie.setYear(movie.getYear());
        movie.setId(id);

        movies.put(id, movie);

        return getById(id);
    }
}

