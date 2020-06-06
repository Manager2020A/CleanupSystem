package com.man.cleanup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import com.man.cleanup.data.CleaningProduct;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CleaningProductCrudTest {

    @Test
    public void testFind() {
        CleaningProduct c = CleaningProduct.findById(new Long(1));

        assertEquals(1, c.getCleaningId());
        assertEquals(1, c.getProductId());
        assertEquals(false, c.isRealized());
    }

    @Test
    @Transactional
    public void testeList() {
        CleaningProduct c = new CleaningProduct();
        c.setCleaningId(1);
        c.setProductId(2);
        c.setRealized(true);
        c.persist();

        List<CleaningProduct> cs = CleaningProduct.listAll();
        assertTrue(cs.size() > 0);

        c.delete();
    }

    @Test
    @Transactional
    public void testCreate() {
        CleaningProduct c = new CleaningProduct();
        c.setCleaningId(1);
        c.setProductId(2);
        c.setRealized(false);
        c.persist();

        Long id = c.getId();
        CleaningProduct c2 = CleaningProduct.findById(id);

        assertEquals(c.getId(), c2.getId());
        assertEquals(c.getCleaningId(), c2.getCleaningId());
        assertEquals(c.getProductId(), c2.getProductId());
        assertEquals(c.isRealized(), c2.isRealized());

        c2.delete();
    }

    @Test
    @Transactional
    public void testUpdate() {
        CleaningProduct c = CleaningProduct.findById(new Long(1));

        int cleaning = c.getCleaningId();
        int product = c.getProductId();
        boolean realized = c.isRealized();

        c.setCleaningId(1);
        c.setProductId(2);
        c.setRealized(!realized);
        c.persist();

        CleaningProduct edit = CleaningProduct.findById(new Long(1));
        // assertNotEquals(cleaning, edit.getCleaningId());
        assertNotEquals(product, edit.getProductId());
        assertNotEquals(realized, edit.isRealized());

        c.setCleaningId(cleaning);
        c.setProductId(product);
        c.setRealized(realized);
        c.persist();
    }

    @Test
    @Transactional
    public void testDelete() {
        CleaningProduct c = new CleaningProduct();
        c.setCleaningId(1);
        c.setProductId(1);
        c.setRealized(false);
        c.persist();

        Long id = c.getId();
        c.delete();

        CleaningProduct del = CleaningProduct.findById(id);
        assertNull(del);
    }

}