package com.man.cleanup.controller;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import com.man.cleanup.data.Cleaning;
import com.man.cleanup.data.CleaningProduct;
import com.man.cleanup.data.CleaningTask;

@ApplicationScoped
public class CleaningController {

    public Cleaning update(Long id, Cleaning c) {
        Cleaning cleaning = Cleaning.findById(id);
        
        if (cleaning == null) {
            throw new NotFoundException("Cleaning with id of " + id + " does not exist.");
        }
        
        cleaning.setName(c.getName());
        cleaning.setNextDate( c.getNextDate() );
        cleaning.setEstimateTime( c.getEstimateTime() );
        cleaning.setDueDate( c.getDueDate() );
        cleaning.setGuidelines( c.getGuidelines() );
        cleaning.setActive( c.isActive() );
        cleaning.setFrequency( c.getFrequency() );
        cleaning.setProducts( c.getProducts()  );
        cleaning.setTasks( c.getTasks() );

        CleaningTask.delete( "ref_cleaning", id );
        CleaningTask.persist( cleaning.getTasks() );

        CleaningProduct.delete( "ref_cleaning", id );
        CleaningProduct.persist( cleaning.getProducts() );
        
        return cleaning;
    }

    public void delete( Long id ){
        Cleaning entity = Cleaning.findById(id);

        if (entity == null) {
            throw new NotFoundException("Cleaning with id " + id + " does not exist.");
        }

        if ( entity.isActive() ){
            throw new InternalError( "Cleaning already inactive");
        }

        entity.setActive(false);
    }

    public void check( Cleaning c ){
        if ( c.getNextDate() == null ){
            throw new IllegalArgumentException( "Cleaning Next date cannot be null" );
        }

        if ( c.getNextDate().isBefore( LocalDate.now() )){
            throw new IllegalArgumentException( "Cleaning Next Date must be after today" );
        }
    }

    
}