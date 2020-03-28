package com.man.cleanup.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.man.cleanup.data.Cleaning;

@ApplicationScoped
public class CleaningController {

    public Cleaning update(Long id, Cleaning c) {
        Cleaning cleaning = Cleaning.findById(id);
        if (cleaning == null) {
            throw new WebApplicationException("Cleaning with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        cleaning.setName(c.getName());
        cleaning.setNextDate( c.getNextDate() );
        cleaning.setEstimateTime( c.getEstimateTime() );
        cleaning.setDueDate( c.getDueDate() );
        cleaning.setGuidelines( c.getGuidelines() );
        cleaning.setActive( c.isActive() );
        cleaning.setFrequency( c.getFrequency() );
        
        return cleaning;
    }
}