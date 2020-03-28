package com.man.cleanup.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Id;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.man.cleanup.controller.CleaningController;
import com.man.cleanup.data.Cleaning;
import com.man.cleanup.data.CleaningTask;

@Path("/cleaning")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CleaningService {

    @Inject
    private CleaningController controller;

    @GET
    public List<Cleaning> findAll() {
        return Cleaning.listAll();
    }

    @GET
    @Path( "{id}" )
    public Cleaning find( @PathParam("id") Long id ) {
        return Cleaning.findById(id );
    }

    @POST
    @Transactional
    public Response create(Cleaning p) {
        Cleaning.persist(p);
        return Response.ok(p).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Cleaning p) {
        Cleaning entity = controller.update(id, p);
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Cleaning entity = Cleaning.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Cleaning with id " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        entity.setActive(false);
        return Response.status(204).build();
    }
}