package com.man.cleanup;

import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import com.man.cleanup.controller.TaskController;
import com.man.cleanup.data.Task;
import com.man.cleanup.util.Errors;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class TaskControllerTest {

    @Inject
    private TaskController controller;

    @Test
    public void testErrorController() {
        Errors errors = controller.check(new Task());

        assertTrue(errors.hasErros(), errors.toString());
    }

    @Test
    public void testValidController() {

        Task task = new Task();
        task.setName("Tarefa teste");
        
        assertFalse(controller.check(task).hasErros());
    }

    @Test
    public void testIsValid(){

        Task t = new Task();
        t.setName("Tarefa teste");
        t.setActive(true);

        assertFalse(controller.isValid(t).hasErros());
    }

    @Test
    public void testIsNotValid(){

        Task t = new Task();
        t.setName("Tarefa teste");
        t.setActive(false);

        Errors errors = controller.isValid(t);
        assertTrue(errors.hasErros(), errors.toString());
    }
}