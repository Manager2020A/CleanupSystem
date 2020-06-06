package com.man.cleanup;

import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import com.man.cleanup.controller.ProductController;
import com.man.cleanup.data.Product;
import com.man.cleanup.util.Errors;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProductControllerTest {

    @Inject
    private ProductController controller;

    @Test
    public void testErrorController() {
        Errors errors = controller.check(new Product());

        assertTrue(errors.hasErros(), errors.toString());
    }

    @Test
    public void testValidController() {

        Product p = new Product();
        p.setName("Produto teste");
        
        assertFalse(controller.check(p).hasErros());
    }

    @Test
    public void testIsValid(){

        Product p = new Product();
        p.setName("Produto teste");
        p.setActive(true);

        assertFalse(controller.isValid(p).hasErros());
    }

    @Test
    public void testIsNotValid(){

        Product p = new Product();
        p.setName("Produto teste");
        p.setActive(false);

        Errors errors = controller.isValid(p);
        assertTrue(errors.hasErros(), errors.toString());
    }
}