package com.zarebcn.api.movies.database;

import com.zarebcn.api.movies.model.Movie;
import com.zarebcn.api.movies.model.Rental;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RentalDaoSpringJdbc {

   /* private JdbcTemplate jdbcTemplate;

    private RowMapper<Rental> mapper;

    public RentalDaoSpringJdbc(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.mapper = new RowMapper<Rental>() {
            @Nullable
            @Override
            public Rental mapRow(ResultSet rs, int rowNum) throws SQLException {

                // Extract a book from current row in ResultSet

                int id = rs.getInt("id");
                int user = rs.getInt("user");
                Date rentalDate = rs.getDate("rental_date");
                int movieId = rs.getInt("movie_id");
 M
                return new Rental(id, user, movieId, rentalDate);
            }
        };
    }*/
}
