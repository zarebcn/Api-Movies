package com.zarebcn.api.movies.model;

public class Rental {

    private int movieid;
    private int userid;

   public Rental() {

   }

    public Rental(int movieid, int userid) {
        this.movieid = movieid;
        this.userid = userid;
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

    @Override
    public String toString() {
        return "movie: " + movieid + " is rented by user: " + userid;
    }
}
