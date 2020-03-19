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
        cleaning.setEstimateDate( c.getEstimateDate() );
        cleaning.setEstimateTime( c.getEstimateTime() );
        cleaning.setRealizedDate( c.getRealizedDate() );
        cleaning.setGuidelines( c.getGuidelines() );
        cleaning.setActive( c.isActive() );
        return cleaning;
    }

    /**
     * This method is main purpose to show simple "Business" example
     * 
     * @param product
     * @return
     */
    public boolean isValid(Cleaning product) {
        return !product.getName().isEmpty();

    }
}