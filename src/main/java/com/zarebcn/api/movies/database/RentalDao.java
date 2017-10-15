package com.zarebcn.api.movies.database;

import com.zarebcn.api.movies.model.Rental;

import java.util.Collection;

public interface RentalDao {

    Collection<Rental> viewRentals();

    Collection<Rental> getRentalsByUserId(int id);

    Rental getById(int id);

    Rental createRental(int id, Rental rental);

    void returnMovie(int rentalId);

}
