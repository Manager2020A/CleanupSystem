package com.man.cleanup.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.man.cleanup.controller.TaskController;
import com.man.cleanup.data.Task;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskService {

    @Inject
    private TaskController controller;

    @GET
    public List<Task> findAll() {
        return Task.listAll();
    }

    @POST
    @Transactional
    public Response create(Task t) {
        
        try
        {
            Task.persist(t);

            System.out.println( t );
        }
        catch( Exception e ) 
        {
            System.out.println( e );
        }

        return Response.ok(t).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Task t) {
        if (!controller.isValid(t)) {
            return Response.ok("Task was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        Task tEntity = controller.update(id, t);
        return Response.ok(tEntity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Task tEntity = Task.findById(id);
        if (tEntity == null) {
            throw new WebApplicationException("Food with id " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        tEntity.delete();
        return Response.status(204).build();
    }
}