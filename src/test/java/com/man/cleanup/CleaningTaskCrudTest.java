package com.man.cleanup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import com.man.cleanup.data.CleaningTask;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CleaningTaskCrudTest {

    @Test
    public void testFind() {
        CleaningTask c = CleaningTask.findById(new Long(1));

        assertEquals(1, c.getCleaningId());
        assertEquals(1, c.getTaskId());
        assertEquals(false, c.isRealized());
    }

    @Test
    @Transactional
    public void testeList() {
        CleaningTask c = new CleaningTask();
        c.setCleaningId(1);
        c.setTaskId(2);
        c.setRealized(true);
        c.persist();

        List<CleaningTask> cs = CleaningTask.listAll();
        assertTrue(cs.size() > 0);

        c.delete();
    }

    @Test
    @Transactional
    public void testCreate() {
        CleaningTask c = new CleaningTask();
        c.setCleaningId(1);
        c.setTaskId(2);
        c.setRealized(false);
        c.persist();

        Long id = c.getId();
        CleaningTask c2 = CleaningTask.findById(id);

        assertEquals(c.getId(), c2.getId());
        assertEquals(c.getCleaningId(), c2.getCleaningId());
        assertEquals(c.getTaskId(), c2.getTaskId());
        assertEquals(c.isRealized(), c2.isRealized());

        c2.delete();
    }

    @Test
    @Transactional
    public void testUpdate() {
        CleaningTask c = CleaningTask.findById(new Long(1));

        int cleaning = c.getCleaningId();
        int task = c.getTaskId();
        boolean realized = c.isRealized();

        c.setCleaningId(1);
        c.setTaskId(2);
        c.setRealized(!realized);
        c.persist();

        CleaningTask edit = CleaningTask.findById(new Long(1));
        // assertNotEquals(cleaning, edit.getCleaningId());
        assertNotEquals(task, edit.getTaskId());
        assertNotEquals(realized, edit.isRealized());

        c.setCleaningId(cleaning);
        c.setTaskId(task);
        c.setRealized(realized);
        c.persist();
    }

    @Test
    @Transactional
    public void testDelete() {
        CleaningTask c = new CleaningTask();
        c.setCleaningId(1);
        c.setTaskId(1);
        c.setRealized(false);
        c.persist();

        Long id = c.getId();
        c.delete();

        CleaningTask del = CleaningTask.findById(id);
        assertNull(del);
    }

}