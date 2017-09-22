package com.zarebcn.api.movies.service;

import com.zarebcn.api.movies.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<Integer, User> users;
    private int nextId;

    public UserService() {

        users = new HashMap<>();
        users.put(1, new User(1));
        users.put(2, new User(2));
        users.put(3, new User(3));
        users.put(4, new User(4));

        nextId = 5;
    }

    public User getById(int id) {

        return users.get(id);
    }

    public int getUserId(int id) {

        return users.get(id).getId();
    }

    public User addUser(User user) {

        user.setId(nextId);
        users.put(nextId, user);
        nextId++;

        return user;
    }

    public void deleteUser(int id) {

        users.remove(id);
    }

    public User editUser(int id, User user) {

        user.setId(id);
        users.put(id, user);

        return getById(id);
    }
}
