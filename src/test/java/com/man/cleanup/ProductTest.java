package com.man.cleanup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import com.man.cleanup.data.Product;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ProductTest{

    @Test
    public void testFind(){
        Product p = Product.findById(new Long(3));

        assertEquals(3, p.getId());
        assertEquals("Detergente", p.getName());
        assertEquals("", p.getBranding());
        assertEquals(500, p.getCapacity());
        assertEquals(true,p.isActive());
    }

    @Test
    @Transactional
    public void testeList(){
        Product p = new Product();
        p.setName("Teste");
        p.persist();

       List<Product> products = Product.listAll();
       assertTrue(products.size() > 0);

       p.delete();
    }

    @Test
    @Transactional
    public void testCreate(){
        Product p = new Product();
        p.setName("Pro\\du/to de t'es\"te");
        p.setBranding("Ma\"rc\\a T'es/te");
        p.setCapacity(123.45);
        p.setActive(false);
        p.persist();

        Long id = p.getId();
        Product p2 = Product.findById(id);

        assertEquals(p.getId(),p2.getId());
        assertEquals(p.getName(),p2.getName());
        assertEquals(p.getBranding(),p2.getBranding());
        assertEquals(p.getCapacity(),p2.getCapacity());
        assertEquals(p.isActive(),p2.isActive());

        p2.delete();
    }

    @Test
    @Transactional
    public void testUpdate(){
        Product p = Product.findById(new Long(3));

        String name = p.getName();
        String branding = p.getBranding();
        double capacity = p.getCapacity();
        boolean active = p.isActive();

        p.setName("Nome Editado");
        p.setBranding("Marca Editada");
        p.setCapacity(123.45);
        p.setActive(!active);
        p.persist();

        Product edit = Product.findById(new Long(3));
        assertNotEquals(name, edit.getName());
        assertNotEquals(branding, edit.getBranding());
        assertNotEquals(capacity, edit.getCapacity());
        assertNotEquals(active, edit.isActive());

        p.setName(name);
        p.setBranding(branding);
        p.setCapacity(capacity);
        p.setActive(active);
        p.persist();
    }

    @Test
    @Transactional
    public void testDelete(){
        Product p = new Product();
        p.setName("Produto deletar");
        p.setCapacity(1);
        p.setActive(false);
        p.persist();

        Long id = p.getId();
        p.delete();

        Product del = Product.findById(id);
        assertNull(del);
    }
}