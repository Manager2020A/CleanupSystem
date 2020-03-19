package com.man.cleanup.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.man.cleanup.data.Task;

@ApplicationScoped
public class TaskController {

    public Task update(Long id, Task t) {
        Task task = Task.findById(id);

        if (task == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }

        task.setName(t.getName());
        task.setGuidelines(t.getGuidelines());
        task.setActive(t.isAtive());

        return task;
    }

    public void check(Task t) {
        if (t == null) {
            throw new WebApplicationException("Task cannot be null", Response.Status.INTERNAL_SERVER_ERROR);
        }

        if (t.getName() == null || t.getName().isEmpty()) {
            throw new WebApplicationException("Task's name cannot be null", Response.Status.INTERNAL_SERVER_ERROR);
        }

        if (!t.isAtive()) {
            throw new WebApplicationException("Task must be active", Response.Status.INTERNAL_SERVER_ERROR);
        }

        if ( t.getId() != null ) {
            if (Task.count("upper( name ) = ?1 and id <> ?2", t.getName().toUpperCase(), t.getId()) > 0) {
                throw new WebApplicationException("Task ''" + t.getName() + "' already exists",
                        Response.Status.INTERNAL_SERVER_ERROR);
            }
        }

        else {
            if (Task.count("upper( name ) = ?1", t.getName().toUpperCase()) > 0) {
                throw new WebApplicationException("Task ''" + t.getName() + "' already exists",
                        Response.Status.INTERNAL_SERVER_ERROR);
            }
        }

    }

    /**
     * This method is main purpose to show simple "Business" example
     * 
     * @param task
     * @return
     */
    public boolean isValid(Task task) {
        return !task.getName().isEmpty();

    }
}