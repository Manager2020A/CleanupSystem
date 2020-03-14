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