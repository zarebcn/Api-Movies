package com.zarebcn.api.movies.model;

public class Movie {

    private int id;
    private String title;
    private String director;
    private int year;

    public Movie() {
        //dropwizard constructor for POST
    }

    public Movie(int id, String title, String director, int year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " by " + director + " (" + year + ")";
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

