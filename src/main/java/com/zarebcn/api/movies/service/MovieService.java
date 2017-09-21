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
        movies.put(1, new Movie(1, "Alien", "Ridley Scott", "https://usercontent2.hubstatic.com/5619775_f520.jpg", 1979, true));
        movies.put(2, new Movie(2, "The Godfather", "Francis Ford Coppola", "http://www.coverwhiz.com/content/The-Godfather.jpg", 1972, true));
        movies.put(3, new Movie(3, "Goodfellas", "Martin Scorsese", "https://images-na.ssl-images-amazon.com/images/I/81NuuGhiNmL._SL1500_.jpg", 1990, true));
        movies.put(4, new Movie(4, "Gangs of New York", "Martin Scorsese", "http://is5.mzstatic.com/image/thumb/Video49/v4/a8/d9/e0/a8d9e0b7-382d-a7c8-5eac-888a2368b83f/source/1200x630bb.jpg", 2002, true));
        movies.put(5, new Movie(5, "Scarface", "Brian De Palma", "http://1.bp.blogspot.com/-36ve8JpWs-g/UHzFtuk53XI/AAAAAAAABhE/sv7Qg1U1wdo/s1600/SCARFACE-KINGPINS-OF-DESIGN.jpg", 1983, true));
        movies.put(6, new Movie(6, "American History X", "Toni Kaye", "http://images6.fanpop.com/image/photos/38800000/American-History-X-Poster-american-history-x-38821272-700-1050.jpg", 1998, true));
        movies.put(7, new Movie(7, "Full metal jacket", "Stanley Kubrick", "https://de1imrko8s7v6.cloudfront.net/movies/posters/full-metal-jacket_movieposter_1376615606.jpg", 1987, true));
        movies.put(8, new Movie(8, "Clockwork orange", "Stanley Kubrick", "https://e.snmc.io/lk/f/l/98c46e730f83adc84a93ff6a003e9d73/2576760.jpg", 1962, true));
        movies.put(9, new Movie(9, "Jaws", "Steven Spielberg", "http://www.coverwhiz.com/content/Jaws.jpg", 1975, true));
        movies.put(10, new Movie(10, "Braveheart", "Mel Gibson", "http://is5.mzstatic.com/image/thumb/Video71/v4/5f/bb/88/5fbb88d0-55d6-88eb-053a-7059fac19fc7/source/1200x630bb.jpg", 1995, true));


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
        movie.setAvailable(true);

        movies.put(nextId, movie);

        nextId++;

        return movie;
    }

    public void deleteMovie(int id) {

        movies.remove(id);
    }

    public Movie editMovie(int id, Movie movie) {

        movie.setId(id);

        movie.setAvailable(movie.isAvailable());

        movies.put(id, movie);

        return getById(id);
    }

    public Movie setRented(int id, Movie movie) {

       // Movie movie = movies.get(id);
        movie.setAvailable(false);
        movies.put(id, movie);

        return getById(id);
    }

    public Movie setAvailable(int id, Movie movie) {

        movie.setAvailable(true);
        movies.put(id, movie);

        return getById(id);
    }
}

