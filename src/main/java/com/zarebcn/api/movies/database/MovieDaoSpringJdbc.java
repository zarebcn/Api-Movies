package com.zarebcn.api.movies.database;

import com.zarebcn.api.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MovieDaoSpringJdbc implements MovieDao{

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Movie> mapper;

    public MovieDaoSpringJdbc(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.mapper = new RowMapper<Movie>() {
            @Nullable
            @Override
            public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {

                // Extract a book from current row in ResultSet

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String director = rs.getString("director");
                String cover = rs.getString("cover");
                int year = rs.getInt("year");
                int copies = rs.getInt("copies");
                int availableCopies = rs.getInt("available_copies");

                return new Movie(id, title, director, cover, year, copies);
            }
        };
    }

    @Override
    public Movie getById(int id) {

        Object[] args = { id };
        return jdbcTemplate.queryForObject("select * from movies where id = ?", args, mapper);
    }

    @Override
    public Collection<Movie> getAll() {

        return jdbcTemplate.query("select * from movies", mapper);
    }

    @Override
    public Collection<Movie> findByTitle(String titlePart) {

        Object[] args = { "%" + titlePart + "%" };
        return jdbcTemplate.query("select * from movies where title like ?", args, mapper);
    }

    @Override
    public Movie addMovie(Movie movie) {

        Object[] args = { movie.getTitle(), movie.getDirector(), movie.getCover(), movie.getYear(), movie.getCopies(), movie.getCopies() };
        jdbcTemplate.update("insert into movies (title, director, cover, year, copies, available_copies) values (?, ?, ?, ?, ?, ?)", args);

        // TODO: get ID of created book
        // book.setId(id);

        return movie;
    }

    @Override
    public String editMovie(int id, Movie movie) {

        Object[] args1 = { id };
        Movie peli = jdbcTemplate.queryForObject("select * from movies where id = ?", args1, mapper);

        if (peli.getCopies() == peli.getAvailableCopies()) {

            Object[] args = { movie.getTitle(), movie.getDirector(), movie.getCover(), movie.getYear(), movie.getCopies(), id };
            jdbcTemplate.update("update movies set title = ?, director = ?, cover = ?, year = ?, copies = ? where id = ?", args);
            return "Movie edited";
        } else {

            return "This movie is actually rented and cant be edited";
        }
    }

    @Override
    public String deleteMovie(int id) {

        Object[] args1 = { id };
        Movie peli = jdbcTemplate.queryForObject("select * from movies where id = ?", args1, mapper);

        if (peli.getCopies() == peli.getAvailableCopies()) {

            Object[] args = { id };
            jdbcTemplate.update("delete from movies where id = ?", args);
            return "Deleted movie";
        } else {

            return "This movie is actually rented and cant be deleted";
        }
    }

    @Override
    public void rentMovie(int id) {

        Object[] args1 = { id };
        Movie peli = jdbcTemplate.queryForObject("select * from movies where id = ?", args1, mapper);

        Object[] args = { peli.getAvailableCopies() - 1, id };
        jdbcTemplate.update("update movies set available_copies = ? where id = ?", args);
    }

    @Override
    public void returnMovie(int id) {

        Object[] args1 = { id };
        Movie peli = jdbcTemplate.queryForObject("select * from movies where id = ?", args1, mapper);

        Object[] args = { peli.getAvailableCopies() + 1, id };
        jdbcTemplate.update("update movies set available_copies = ? where id = ?", args);
    }

}
