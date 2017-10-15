package com.zarebcn.api.movies.model;

public class Movie {

    private int id;
    private String title;
    private String director;
    private String cover;
    private int year;
    private int copies;
    private int availableCopies;

    public Movie() {
        //dropwizard constructor for POST
    }

    public Movie(int id, String title, String director, String cover, int year, int copies, int availableCopies) {

        this.id = id;
        this.title = title;
        this.director = director;
        this.cover = cover;
        this.year = year;
        this.copies = copies;
        this.availableCopies = availableCopies;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    @Override
    public String toString() {
        return title + " by " + director + " (" + year + ")";
    }
}

