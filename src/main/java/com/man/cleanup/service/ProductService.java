package com.man.cleanup.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.man.cleanup.controller.ProductController;
import com.man.cleanup.data.Product;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductService {

    @Inject
    private ProductController controller;

    @GET
    public List<Product> findAll() {
        return Product.listAll();
    }

    @POST
    @Transactional
    public Response create(Product p) {
        Product.persist(p);
        return Response.ok(p).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Product p) {
        if (!controller.isValid(p)) {
            return Response.ok("Product was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        Product entity = controller.update(id, p);
        return Response.ok(entity).build();
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