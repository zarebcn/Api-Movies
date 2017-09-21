package com.zarebcn.api.movies.model;

public class User {

    private int id;

    public User() {

    }

    public User(int id) {

        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "user id: " + id;
    }
}
