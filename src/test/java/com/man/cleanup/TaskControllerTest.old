package com.man.cleanup;
import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.transaction.Transactional;
import com.man.cleanup.data.Task;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TaskControllerTest {

    @Test
    // Busca a tarefa de id '3' e compara, vê se está ativa. 
    public void testFind() {
        Task t = Task.findById(new Long(3));

        assertEquals(3, t.getId());
        assertEquals(true, t.isActive());
    }

    // Insere a tarefa e verifica se o listar está funcionando, após deleta
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

    // Teste de insert, verificar se está funcionado os caracteres especias
    @Test
    @Transactional
    public void testCreate() {
        Task t = new Task();
        t.setName("Tare\\fa/ de t'es\"te");
        t.setActive(false);
        t.persist();

        Long id = t.getId();
        Task t2 = Task.findById(id);

        assertEquals(t.getId(), t2.getId());
        assertEquals(t.getName(), t2.getName());
        assertEquals(t.isActive(), t2.isActive());

        t2.delete();
    }

    // Testa se está sendo possível editar uma tarefa, salva o nome em uma variável, edita e após compara
    // Se esta diferente, teste passou, volta no nome da tarefa

    @Test
    @Transactional
    public void testUpdate() {
        Task t = Task.findById(new Long(3));

        String name = t.getName();
        boolean active = t.isActive();

        t.setName("Nome Editado");
        t.setActive(!active);
        t.persist();

        Task edit = Task.findById(new Long(3));
        assertNotEquals(name, edit.getName());
        assertNotEquals(active, edit.isActive());

        t.setName(name);
        t.setActive(active);
        t.persist();
    }

    // Verifica se o delete lógico está funcionado. Insere uma tarefa, seta para inativa e após deleta

    @Test
    @Transactional
    public void testDelete() {
        Task t = new Task();
        t.setName("Tarefa deletar");
        t.setActive(false);
        t.persist();

        Long id = t.getId();
        t.delete();

        Task del = Task.findById(id);
        assertNull(del);
    }

}