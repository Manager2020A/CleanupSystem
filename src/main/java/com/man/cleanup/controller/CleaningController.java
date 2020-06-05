package com.man.cleanup.controller;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import com.man.cleanup.data.Cleaning;
import com.man.cleanup.data.CleaningProduct;
import com.man.cleanup.data.CleaningTask;
import com.man.cleanup.util.Errors;

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

    public Errors check( Cleaning c ){
        Errors errros = new Errors();

        if ( c.getNextDate() == null ){
            errros.addError( "Necessário definir uma data para a faxina!" );
        }

        else if ( c.getNextDate().isBefore( LocalDate.now() )){
            errros.addError( "Necessário definir uma data maior que a data atual!");
        }

        return errros;
    }

    /**
     * This method is main purpose to show simple "Business" example
     * 
     * @param product
     * @return
     */
    public Errors isValid(Cleaning c) {
        Errors errors = new Errors();

        if (c == null) {
            errors.addError("Limpeza não pode ser nula!");
        }

        if (c.getName() == null || c.getName().isEmpty()) {
            errors.addError("Informe um nome para a limpeza!");
        }

        if (!c.isActive()) {
            errors.addError("Limpeza não pode ser inativa!");
        }

        return errors;
    }

    
}