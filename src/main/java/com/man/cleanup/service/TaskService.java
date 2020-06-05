package com.man.cleanup.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.man.cleanup.controller.TaskController;
import com.man.cleanup.data.Task;
import com.man.cleanup.util.Errors;

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

        try {
            controller.check(t);

            Task.persist(t);

            return Response.ok(t).status(201).build();
        }

        catch (Exception e) {
            return Response.serverError().entity(e).build();
        }

    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Task t) {

        Errors e = controller.isValid( t );
    
        if (e.hasErros() ){
            return Response.serverError().entity( e.toString() ).build();
        }

        // if (!controller.isValid(t)) {
        //     return Response.ok("Task was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        // }
        
        Task entity = controller.update(id, t);
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Task entity = Task.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Food with id " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        entity.setActive(false);
        return Response.status(204).build();
    }
}