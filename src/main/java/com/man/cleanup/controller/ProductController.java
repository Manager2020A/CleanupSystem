package com.man.cleanup.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.man.cleanup.data.Product;

@ApplicationScoped
public class ProductController {

    public Product update(Long id, Product p) {
        Product product = Product.findById(id);
        if (product == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        product.setName(p.getName());
        product.setBranding(p.getBranding());
        product.setCapacity(p.getCapacity());
        product.setActive( p.isActive() );
        return product;
    }

    /**
     * This method is main purpose to show simple "Business" example
     * 
     * @param product
     * @return
     */
    public boolean isValid(Product product) {
        return !product.getName().isEmpty();
    }
}