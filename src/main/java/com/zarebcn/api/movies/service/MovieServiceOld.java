package com.zarebcn.api.movies.service;

import com.zarebcn.api.movies.model.Movie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieServiceOld {

    private Map<Integer, Movie> movies;
    private int nextId;

    public MovieServiceOld() {

        movies = new HashMap<>();
        movies.put(1, new Movie(1, "Alien", "Ridley Scott", "https://usercontent2.hubstatic.com/5619775_f520.jpg", 1979, 3));
        movies.put(2, new Movie(2, "The Godfather", "Francis Ford Coppola", "http://www.coverwhiz.com/content/The-Godfather.jpg", 1972, 3));
        movies.put(3, new Movie(3, "Goodfellas", "Martin Scorsese", "https://images-na.ssl-images-amazon.com/images/I/81NuuGhiNmL._SL1500_.jpg", 1990, 3));
        movies.put(4, new Movie(4, "Gangs of New York", "Martin Scorsese", "http://is5.mzstatic.com/image/thumb/Video49/v4/a8/d9/e0/a8d9e0b7-382d-a7c8-5eac-888a2368b83f/source/1200x630bb.jpg", 2002, 3));
        movies.put(5, new Movie(5, "Scarface", "Brian De Palma", "http://1.bp.blogspot.com/-36ve8JpWs-g/UHzFtuk53XI/AAAAAAAABhE/sv7Qg1U1wdo/s1600/SCARFACE-KINGPINS-OF-DESIGN.jpg", 1983, 3));
        movies.put(6, new Movie(6, "American History X", "Toni Kaye", "http://images6.fanpop.com/image/photos/38800000/American-History-X-Poster-american-history-x-38821272-700-1050.jpg", 1998, 3));
        movies.put(7, new Movie(7, "Full metal jacket", "Stanley Kubrick", "https://de1imrko8s7v6.cloudfront.net/movies/posters/full-metal-jacket_movieposter_1376615606.jpg", 1987, 3));
        movies.put(8, new Movie(8, "Clockwork orange", "Stanley Kubrick", "https://e.snmc.io/lk/f/l/98c46e730f83adc84a93ff6a003e9d73/2576760.jpg", 1962, 3));
        movies.put(9, new Movie(9, "Jaws", "Steven Spielberg", "http://www.coverwhiz.com/content/Jaws.jpg", 1975, 3));
        movies.put(10, new Movie(10, "Braveheart", "Mel Gibson", "http://is5.mzstatic.com/image/thumb/Video71/v4/5f/bb/88/5fbb88d0-55d6-88eb-053a-7059fac19fc7/source/1200x630bb.jpg", 1995, 3));

        nextId = 11;
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
        movie.setAvailableCopies(movie.getCopies());
        movies.put(nextId, movie);
        nextId++;

        return movie;
    }

    public String deleteMovie(int id) {

        if (movies.get(id).getCopies() == movies.get(id).getAvailableCopies()) {

            movies.remove(id);
            return "Deleted movie";

        } else {

            return "This movie is actually rented and cant be deleted";
        }
    }

    public String editMovie(int id, Movie movie) {

        if (movies.get(id).getCopies() == movies.get(id).getAvailableCopies()) {

            movie.setId(id);
            movie.setAvailableCopies(movie.getCopies());
            movies.put(id, movie);

            return "Movie edited";

        } else {

            return "This movie is actually rented and cant be edited";
        }
    }

    public void rentMovie(int id) {

        Movie movie = movies.get(id);
        movie.setAvailableCopies(movie.getAvailableCopies() - 1);
        movies.put(id, movie);
    }

    public void returnMovie(int id) {

        Movie movie = movies.get(id);
        movie.setAvailableCopies(movie.getAvailableCopies() + 1);
        movies.put(id, movie);
    }
}

