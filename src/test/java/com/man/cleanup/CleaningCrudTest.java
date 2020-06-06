package com.man.cleanup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.man.cleanup.controller.CleaningController;
import com.man.cleanup.data.Cleaning;
import com.man.cleanup.util.Errors;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CleaningCrudTest {

    @Inject
    private CleaningController controller;

    @Test
    public void testFind() {
        Cleaning c = Cleaning.findById(new Long(1));

        assertEquals(1, c.getId());
        assertEquals("Limpeza Semanal", c.getName());
        assertEquals("limpar com cuidado", c.getGuidelines());
        assertEquals( null, c.getDueDate());
        assertEquals(LocalTime.of(1, 0, 0), c.getEstimateTime());
        assertEquals(true, c.isActive());
        assertEquals(Cleaning.Frequency.MANUAL, c.getFrequency());
    }

    @Test
    @Transactional
    public void testeList() {
        Cleaning c = new Cleaning();
        c.setName("Teste");
        c.setNextDate(LocalDate.now());
        c.setDueDate(null);
        c.setEstimateTime(LocalTime.of(1, 0, 0));
        c.setFrequency(Cleaning.Frequency.MONTH);
        c.setActive(true);
        c.persist();

        List<Cleaning> Cleanings = Cleaning.listAll();
        assertTrue(Cleanings.size() > 0);

        c.delete();
    }

    @Test
    @Transactional
    public void testCreate() {
        Cleaning c = new Cleaning();
        c.setName("Limp\\e/za de t'es\"te");
        c.setGuidelines("Obs\"er\\vação T'es/te");
        c.setDueDate(null);
        c.setEstimateTime(LocalTime.of(1, 0, 0));
        c.setNextDate(LocalDate.of(2020, 6, 1));
        c.setFrequency(Cleaning.Frequency.MONTH);
        c.setActive(true);
        c.persist();

        Long id = c.getId();
        Cleaning c2 = Cleaning.findById(id);

        assertEquals(c.getId(), c2.getId());
        assertEquals(c.getName(), c2.getName());
        assertEquals(c.getGuidelines(), c2.getGuidelines());
        assertEquals(c.getNextDate(), c2.getNextDate());
        assertEquals(c.isActive(), c2.isActive());

        c2.delete();
    }

    @Test
    @Transactional
    public void testUpdate() {
        Cleaning p = Cleaning.findById(new Long(1));

        String name = p.getName();
        String guidelines = p.getGuidelines();
        boolean active = p.isActive();

        p.setName("Nome Editado");
        p.setGuidelines("Guia editado");
        p.setActive(!active);
        p.persist();

        Cleaning edit = Cleaning.findById(new Long(1));
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
        Cleaning p = new Cleaning();
        p.setName("Limpeza deletar");
        p.setGuidelines("Desc Limpeza deletar");
        p.setDueDate(null);
        p.setEstimateTime(LocalTime.of(1, 0, 0));
        p.setNextDate(LocalDate.of(2020, 6, 1));
        p.setFrequency(Cleaning.Frequency.MONTH);

        p.persist();

        Long id = p.getId();
        p.delete();

        Cleaning del = Cleaning.findById(id);
        assertNull(del);
    }

    @Test
    @Transactional
    public void testController() {
        Cleaning p = new Cleaning();
        p.setName("Limpeza");
        p.setGuidelines("Desc limpeza");
        p.setEstimateTime(LocalTime.of(1, 0, 0));
        p.setNextDate(LocalDate.of(2020, 9, 1));
        p.setActive(true);

        Errors errors = controller.isValid(p);

        assertFalse(errors.hasErros(), errors.toString());

        if (!errors.hasErros()) {
            p.persist();
        }

        Cleaning p1 = new Cleaning();
        p1.setName("Limpeza");
        p1.setGuidelines("Desc limpeza");
        p1.setActive(true);

        errors = controller.isValid(p1);

        assertFalse(errors.hasErros(), errors.toString());

        p.delete();
    }
}