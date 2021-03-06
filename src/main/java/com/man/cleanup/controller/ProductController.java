package com.man.cleanup.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.man.cleanup.data.Product;
import com.man.cleanup.util.Errors;

@ApplicationScoped
public class ProductController {

    public Product update(Long id, Product p) {
        Product product = Product.findById(id);
        if (product == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.",
                    Response.Status.NOT_FOUND);
        }
        product.setName(p.getName());
        product.setBranding(p.getBranding());
        product.setCapacity(p.getCapacity());
        product.setActive(p.isActive());
        return product;
    }

    public Errors check(Product p){
        Errors errors = new Errors();

        if (p == null) {
            errors.addError("Produto não pode ser nulo");
        } else if(p.getName() == null || p.getName().trim().isEmpty()){
            errors.addError("Nome do produto não pode ser nulo");
        }

        return errors;

    }

    /**
     * This method is main purpose to show simple "Business" example
     * 
     * @param product
     * @return
     */
    public Errors isValid(Product product) {
        Errors errors = new Errors();

        if (product == null) {
            errors.addError("Produto não pode ser nulo!");
        } else if (product.getName() == null || product.getName().isEmpty()) {
            errors.addError("Informe um nome para o produto!");
        } else if (!product.isActive()) {
            errors.addError("Produto não pode ser inativo!");
        } else if (product.getId() != null && product.getId() > 0l) {
            if (Product.count("upper( name ) = ?1 and upper( branding ) = ?2 and active is true and id <> ?3",
                    product.getName().toUpperCase(), product.getBranding().toUpperCase(), product.getId()) > 0) {
                errors.addError("Produto já existe!");
            }
        } else {
            if (Product.count("upper( name ) = ?1 and upper( branding ) = ?2 and active is true",
                    product.getName().toUpperCase(), product.getBranding().toUpperCase()) > 0) {
                errors.addError("Produto já existe!");
            }
        }

        return errors;
    }
}