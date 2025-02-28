package edu.eci.cvds.elysium.repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import edu.eci.cvds.elysium.model.RecursoModel;

import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class RecursoRepositoryTest {

    @Autowired
    private RecursoRepository recursoRepository;

    @AfterEach
    void cleanUp() {
        recursoRepository.deleteAll();
    }

    @Test
    void testSaveAndFindById() {
        RecursoModel recurso = new RecursoModel("REC-100", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        recursoRepository.save(recurso);
        Optional<RecursoModel> found = recursoRepository.findById("REC-100");
        assertTrue(found.isPresent());
        assertEquals("Computadores MAC", found.get().getNombre());
    }

    @Test
    void testUpdateRecurso() {
        RecursoModel recurso = new RecursoModel("REC-101", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        recursoRepository.save(recurso);
        recurso.setNombre("Computadores Windows");
        recurso.setCantidad(15);
        recurso.setEspecificaciones(Arrays.asList("Sistema: Windows", "Almacenamiento: 512GB"));
        recursoRepository.save(recurso);
        Optional<RecursoModel> updated = recursoRepository.findById("REC-101");
        assertTrue(updated.isPresent());
        assertEquals("Computadores Windows", updated.get().getNombre());
        assertEquals(15, updated.get().getCantidad());
    }

    @Test
    void testDeleteRecurso() {
        RecursoModel recurso = new RecursoModel("REC-102", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        recursoRepository.save(recurso);
        recursoRepository.delete(recurso);
        Optional<RecursoModel> found = recursoRepository.findById("REC-102");
        assertFalse(found.isPresent());
    }
}