package com.man.cleanup;


import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.man.cleanup.controller.CleaningController;
import com.man.cleanup.data.Cleaning;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class CleaningControllerTest {

    @Inject 
    private CleaningController controller;

    //@Inject 
    private Cleaning cleaning = new Cleaning();    

    @Test
    public void testErrorController() {
        
        assertThrows(IllegalArgumentException.class, () -> controller.check(cleaning));
    }

    @Test
    public void testValidController() {

        cleaning = new Cleaning();
        cleaning.setActive(true);
        cleaning.setDueDate(null);
        cleaning.setEstimateTime(LocalTime.of(1, 0, 0));
        cleaning.setNextDate(LocalDate.of(2020, 6, 1));
        cleaning.setFrequency(Cleaning.Frequency.MONTH);
        cleaning.setGuidelines("Teste");
        cleaning.setName("Teste");

        assertDoesNotThrow(() -> controller.check(cleaning));
    }

    @Test
    @Transactional
    public void testTransaction() {
        try
        {
            cleaning.delete(); 
        }

        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

}