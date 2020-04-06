package com.man.cleanup;

import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.inject.Inject;

import com.man.cleanup.controller.CleaningController;
import com.man.cleanup.data.Cleaning;
import com.man.cleanup.util.Errors;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class CleaningControllerTest {

    @Inject
    private CleaningController controller;

    @Test
    public void testErrorController() {
        Errors errors = controller.check(new Cleaning());

        assertTrue(errors.hasErros(), errors.toString());
    }

    @Test
    public void testValidController() {

        Cleaning cleaning = new Cleaning();
        cleaning.setActive(true);
        cleaning.setDueDate(null);
        cleaning.setEstimateTime(LocalTime.of(1, 0, 0));
        cleaning.setNextDate(LocalDate.of(2020, 6, 1));
        cleaning.setFrequency(Cleaning.Frequency.MONTH);
        cleaning.setGuidelines("Teste");
        cleaning.setName("Teste");

        assertFalse(controller.check(cleaning).hasErros());
    }
}