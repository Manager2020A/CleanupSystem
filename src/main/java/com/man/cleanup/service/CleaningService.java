package com.man.cleanup.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.man.cleanup.controller.CleaningController;
import com.man.cleanup.data.Cleaning;

@Path("/cleaning")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CleaningService {

    @Inject
    private CleaningController controller;

    @GET
    public List<Cleaning> findAll() {
        return Cleaning.find( "active", true ).list();
    }

    @GET
    @Path("/name/{name}")
    public List<Cleaning> findName(@PathParam("name") String name) {
        return Cleaning.list( "name", name );
    }

    @GET
    @Path("/pendency")
    public List<Cleaning> findPendency() {
        return Cleaning.list( "due_date is null" );
    }

    @GET
    @Path("/active/{active}")
    public List<Cleaning> findAllActives(@PathParam("active") Boolean active){
        return Cleaning.list("active", true);
    }


    @GET
    @Path("{id}")
    public Cleaning find(@PathParam("id") Long id) {
        return Cleaning.findById(id);
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
        controller.delete( id );
        return Response.status(204).build();
    }
}