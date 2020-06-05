package com.man.cleanup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.man.cleanup.controller.TaskController;
import com.man.cleanup.data.Task;
import com.man.cleanup.util.Errors;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TaskCrudTest {

    @Inject
    private TaskController controller;

    @Test
    public void testFind() {
        Task p = Task.findById(new Long(1));

        assertEquals(1, p.getId());
        assertEquals("Arrumar camas", p.getName());
        assertEquals(true, p.isActive());
    }

    @Test
    @Transactional
    public void testeList() {
        Task p = new Task();
        p.setName("Teste");
        p.persist();

        List<Task> tasks = Task.listAll();
        assertTrue(tasks.size() > 0);

        p.delete();
    }

    @Test
    @Transactional
    public void testCreate() {
        Task p = new Task();
        p.setName("Ta\\re/fa de t'es\"te");
        p.setGuidelines("sadasdas");
        p.setActive(false);
        p.persist();

        Long id = p.getId();
        Task p2 = Task.findById(id);

        assertEquals(p.getId(), p2.getId());
        assertEquals(p.getName(), p2.getName());
        assertEquals(p.getGuidelines(), p2.getGuidelines());
        assertEquals(p.isActive(), p2.isActive());

        p2.delete();
    }

    @Test
    @Transactional
    public void testUpdate() {
        Task p = Task.findById(new Long(1));

        String name = p.getName();
        String guidelines = p.getGuidelines();
        boolean active = p.isActive();

        p.setName("Nome Editado");
        p.setGuidelines("Desc Editada");
        p.setActive(!active);
        p.persist();

        Task edit = Task.findById(new Long(1));
        assertNotEquals(name, edit.getName());
        assertNotEquals(guidelines, edit.getGuidelines());
        assertNotEquals(active, edit.isActive());

        p.setName(name);
        p.setGuidelines(guidelines);
        p.setActive(active);
        p.persist();
    }

    @Test
    @Transactional
    public void testDelete() {
        Task p = new Task();
        p.setName("Tarefa deletar");
        p.setGuidelines("DASas");
        p.setActive(false);
        p.persist();

        Long id = p.getId();
        p.delete();

        Task del = Task.findById(id);
        assertNull(del);
    }

    @Test
    @Transactional
    public void testController() {
        Task p = new Task();
        p.setName("Produto");
        p.setGuidelines("teste");

        Errors errors = controller.isValid(p);

        assertFalse( errors.hasErros(), errors.toString());

        if (!errors.hasErros()) {
            p.persist();
        }

        Task p1 = new Task();
        p1.setName("Produto");
        p1.setGuidelines("teste");

        errors = controller.isValid(p1);

        assertTrue(errors.hasErros(), errors.toString());

        p.delete();
    }
}