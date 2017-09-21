package com.zarebcn.api.movies.controller;


import com.zarebcn.api.movies.model.Movie;
import com.zarebcn.api.movies.model.Rental;
import com.zarebcn.api.movies.service.RentalService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("/rental")
@Produces(MediaType.APPLICATION_JSON)
public class RentalApi {

    private RentalService rentalService;

    public RentalApi(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GET
    public Collection<Rental> viewRentals() {
        return rentalService.viewRentals();
    }

    @GET
    @Path("{id}")
    public Rental viewRental(@PathParam("id") int id) {
        return rentalService.getById(id);
    }

    @GET
    @Path("/user/{id}")
    public Collection<Rental> getRentalsByUserId(@PathParam("id") int id) {
        return rentalService.getRentalsByUserId(id);
    }

    @POST
    public Rental createRental(Rental rental) {
        return rentalService.createRental(rental);
    }

    @DELETE
    @Path("{id}")
    public void returnMovie(@PathParam("id") int id) {
        rentalService.returnMovie(id);
    }

}
