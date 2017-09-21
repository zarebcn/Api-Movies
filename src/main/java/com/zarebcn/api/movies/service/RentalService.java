package com.zarebcn.api.movies.service;

import com.zarebcn.api.movies.model.Movie;
import com.zarebcn.api.movies.model.Rental;

import java.util.*;
import java.util.stream.Collectors;

public class RentalService {

    private Map<Integer, Rental> rentals;
    private int nextId;

    public RentalService() {

        rentals = new HashMap<>();
        nextId = 1;
    }

    public Collection<Rental> viewRentals() {

        return rentals.values();
    }

    public Collection<Rental> getRentalsByUserId(int id) {

        List<Rental> provisional = new ArrayList<>(rentals.values());
        Collection<Rental> rentalsByUser;
        List<Rental> provisional2 = new ArrayList<>();
        Rental rental = new Rental();

        for (int i = 0; i < provisional.size(); i++) {

            rental = provisional.get(i);

            if (rental.getUserid()  == id) {

                provisional2.add(rental);
            }
        }

        rentalsByUser = provisional2;

       return rentalsByUser;
    }

    public Rental getById(int id) {
        return rentals.get(id);
    }

    public Rental createRental(Rental rental) {

        rental.setRentalid(nextId);
        rentals.put(nextId, rental);
        nextId++;
        return rental;
    }

    public void returnMovie(int rentalid) {

        rentals.remove(rentalid);
    }
}
