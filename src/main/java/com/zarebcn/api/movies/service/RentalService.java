package com.zarebcn.api.movies.service;

import com.zarebcn.api.movies.model.Rental;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RentalService {

    private Map<Integer, Rental> rentals;
    private int nextId;

    public RentalService() {

        rentals = new HashMap<>();
        nextId = 1;
    }

    public Collection<Rental> viewRentals() {

        Collection<Rental> rent = rentals.values();
        return rent;
    }

    public Rental getById(int id) {
        return rentals.get(id);
    }

    public Rental createRental(Rental rental) {

        rentals.put(nextId, rental);
        nextId++;
        return rental;
    }

    public Collection<Rental> returnMovie(int rentalid) {

        Collection<Rental> pelis;
        rentals.remove(rentalid);
        pelis = rentals.values();
        return pelis;
    }
}
