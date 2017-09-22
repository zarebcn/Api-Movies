package com.zarebcn.api.movies.model;

public class Rental {

   // private int userid;
    private Movie movie;
    private int rentalId;
    private User user;

   public Rental() {

   }

    public Rental(User user, Movie movie, int rentalId) {

        this.rentalId = rentalId;
        this.movie = movie;
        this.user = user;
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
}
