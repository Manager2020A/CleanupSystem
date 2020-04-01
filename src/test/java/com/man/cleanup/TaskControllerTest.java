package com.man.cleanup;


import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.man.cleanup.controller.TaskController;
import com.man.cleanup.data.Task;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class TaskControllerTest {

    @Inject 
    private TaskController controller;

    //@Inject 
    private Task task = new Task();    

    @Test
    public void testErrorController() {
        
        assertThrows(IllegalArgumentException.class, () -> controller.check(task));
    }

    @Test
    public void testValidController() {

        task = new Task();
        task.setActive(true);
        task.setGuidelines("Teste");
        task.setName("Teste");

        assertDoesNotThrow(() -> controller.check(task));
    }

    @Test
    @Transactional
    public void testTransaction() {
        try
        {
            task.delete(); 
        }

        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

}