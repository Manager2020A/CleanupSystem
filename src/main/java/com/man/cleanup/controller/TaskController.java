package com.man.cleanup.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.man.cleanup.data.Task;
import com.man.cleanup.util.Errors;

@ApplicationScoped
public class TaskController {

    public Task update(Long id, Task t) {
        Task task = Task.findById(id);

        if (task == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }

        task.setName(t.getName());
        task.setGuidelines(t.getGuidelines());
        task.setActive(t.isActive());

        return task;
    }

    public Errors check(Task t) {
        Errors errors = new Errors();

        if (t == null) {
            errors.addError("Tarefa não pode ser nula");
        }else if (t.getName() == null || t.getName().isEmpty()) {
            errors.addError("Nome da tarefa não pode ser nula");
        }else if ( t.getId() != null ) {
            if (Task.count("upper( name ) = ?1 and id <> ?2 and active is true", t.getName().toUpperCase(), t.getId()) > 0) {
                errors.addError("Tarefa " + t.getName() + " já existe");
            }
        }else {
            if (Task.count("upper( name ) = ?1 and active is true" , t.getName().toUpperCase()) > 0) {
                errors.addError("Tarefa " + t.getName() + " já existe");
            }
        }

        return errors;
        
    }

    /**
     * This method is main purpose to show simple "Business" example
     * 
     * @param product
     * @return
     */
    public Errors isValid(Task task) {
        Errors errors = new Errors();

        if (task == null) {
            errors.addError("Tarefa não pode ser nula!");
        }

        if (task.getName() == null || task.getName().isEmpty()) {
            errors.addError("Informe um nome para a tarefa!");
        }

        if (!task.isActive()) {
            errors.addError("Tarefa não pode ser inativa!");
        }

        return errors;
    }
}