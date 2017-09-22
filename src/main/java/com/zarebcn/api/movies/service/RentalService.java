package com.zarebcn.api.movies.service;

import com.zarebcn.api.movies.model.Rental;

import java.util.*;

public class RentalService {

    private Map<Integer, Rental> rentals;
    private int nextId;
    //nuevo
    private UserService userService;

    public RentalService(UserService userService) {

        rentals = new HashMap<>();
        this.userService = userService;
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

            if (rental.getUserId()  == id) {

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

        rental.setRentalId(nextId);
        //nuevo
       /* if (userService.getUserId(rental.getUserId()) != rental.getUserId()) {
            return null;
        }*/

        rentals.put(nextId, rental);
        nextId++;
        return rental;
    }

    public void returnMovie(int rentalId) {

        rentals.remove(rentalId);
    }
}
