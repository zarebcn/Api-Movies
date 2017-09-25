package com.zarebcn.api.movies.model;

import java.util.Date;

public class Rental {

   // private int userid;
    private Movie movie;
    private int rentalId;
    private User user;
    private Date rentalDate;

   public Rental() {

   }

    public Rental(User user, Movie movie, int rentalId, Date rentalDate) {

        this.rentalId = rentalId;
        this.movie = movie;
        this.user = user;
        this.rentalDate = rentalDate;
    }

    public int getMovieId() {
        return movie.getId();
    }

    public Movie getMovie() {
       return movie;
    }

    public void setRentalId(int rentalid) {
        this.rentalId = rentalid;
    }

    public int getRentalId() {
        return rentalId;
    }

    @Override
    public String toString() {
        return "movie: " + movie.getId() + " is rented by user: " + user.getId();
    }

    public User getUser() {
        return user;
    }

    public int getUserId() {
        return user.getId();
    }

    public Date getRentalDate() {
        return rentalDate;
    }
}
