package com.zarebcn.api.movies.controller;


import com.zarebcn.api.movies.model.Rental;
import com.zarebcn.api.movies.service.RentalService;
import com.zarebcn.api.movies.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

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

    @POST
    public Rental createRental(Rental rental) {
        return rentalService.createRental(rental);
    }

    @DELETE
    @Path("{id}")
    public Collection<Rental> returnMovie(@PathParam("id") int id) {
        return rentalService.returnMovie(id);
    }

}
