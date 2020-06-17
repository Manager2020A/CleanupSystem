package com.man.cleanup.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.man.cleanup.controller.ProductController;
import com.man.cleanup.data.Product;
import com.man.cleanup.util.Errors;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductService {

    @Inject
    private ProductController controller;

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id){
        Product p = Product.findById(id);
        if(p == null){
            return Response.status(404).build();
        }else{
            return Response.ok(p).build();
        }
    }

    @GET
    public List<Product> findAll() {
        return Product.listAll();
    }

    @GET
    @Path("/teste")
    public List<Product> findAllTeste() {
        return Product.listAll();
    }

    @GET
    @Path("/active")
    public List<Product> findAllActives(){
        return Product.list("active", true);
    }

    @POST
    @Transactional
    public Response create(Product p) {
        try{
            controller.check(p);
            Product.persist(p);
            return Response.ok(p).status(201).build();
        }catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Product p) {

        Errors e = controller.isValid( p );
    
        if (e.hasErros() ){
            return Response.serverError().entity( e.toString() ).build();
        }
        
        return Response.ok( controller.update(id, p) ).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Product entity = Product.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Food with id " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        entity.setActive(false);
        return Response.status(204).build();
    }
}