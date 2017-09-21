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

        rental.setRentalid(nextId);
        rentals.put(nextId, rental);
        nextId++;
        return rental;
    }

    public void returnMovie(int rentalid) {

        rentals.remove(rentalid);
    }
}
