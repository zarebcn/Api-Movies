package com.zarebcn.api.movies.model;

import com.zarebcn.api.movies.service.MovieService;

public class Rental {

    private int movieid;
    private int userid;
    private Movie movie;
    private int rentalid;

   public Rental() {

   }

    public Rental(int movieid, int userid, int rentalid) {
        this.rentalid = rentalid;
        this.movieid = movieid;
        this.userid = userid;
        MovieService movieService = new MovieService();
        this.movie = movieService.getById(movieid);
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public Movie getMovie() {
       return movie;
    }

    public void setRentalid(int rentalid) {
        this.rentalid = rentalid;
    }

    public int getRentalid() {
        return rentalid;
    }

    @Override
    public String toString() {
        return "movie: " + movieid + " is rented by user: " + userid;
    }
}
