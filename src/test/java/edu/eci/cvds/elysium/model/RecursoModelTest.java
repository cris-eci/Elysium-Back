package edu.eci.cvds.elysium.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class RecursoModelModelTest {

    @Test
    void testConstructorAndGetters() {
        List<String> specs = Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB");
        RecursoModel RecursoModel = new RecursoModel("REC-001", "Computadores MAC", 10, specs);
        assertEquals("REC-001", RecursoModel.getId());
        assertEquals("Computadores MAC", RecursoModel.getNombre());
        assertEquals(10, RecursoModel.getCantidad());
        assertEquals(specs, RecursoModel.getEspecificaciones());
    }

    @Test
    void testActualizarSoloNombre() {
        List<String> specs = Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB");
        RecursoModel RecursoModel = new RecursoModel("REC-001", "Computadores MAC", 10, specs);
        RecursoModel.actualizar("Computadores Windows", null, null);
        assertEquals("Computadores Windows", RecursoModel.getNombre());
        assertEquals(10, RecursoModel.getCantidad());
        assertEquals(specs, RecursoModel.getEspecificaciones());
    }

    @Test
    void testActualizarSoloCantidad() {
        List<String> specs = Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB");
        RecursoModel RecursoModel = new RecursoModel("REC-001", "Computadores MAC", 10, specs);
        RecursoModel.actualizar(null, 15, null);
        assertEquals("Computadores MAC", RecursoModel.getNombre());
        assertEquals(15, RecursoModel.getCantidad());
        assertEquals(specs, RecursoModel.getEspecificaciones());
    }

    @Test
    void testActualizarSoloEspecificaciones() {
        List<String> specs = Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB");
        RecursoModel RecursoModel = new RecursoModel("REC-001", "Computadores MAC", 10, specs);
        List<String> newSpecs = Arrays.asList("Sistema: macOS", "Almacenamiento: 512GB");
        RecursoModel.actualizar(null, null, newSpecs);
        assertEquals("Computadores MAC", RecursoModel.getNombre());
        assertEquals(10, RecursoModel.getCantidad());
        assertEquals(newSpecs, RecursoModel.getEspecificaciones());
    }

    @Test
    void testActualizarTodosCampos() {
        List<String> specs = Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB");
        RecursoModel RecursoModel = new RecursoModel("REC-001", "Computadores MAC", 10, specs);
        List<String> newSpecs = Arrays.asList("Sistema: Windows", "Almacenamiento: 512GB");
        RecursoModel.actualizar("Computadores Windows", 20, newSpecs);
        assertEquals("Computadores Windows", RecursoModel.getNombre());
        assertEquals(20, RecursoModel.getCantidad());
        assertEquals(newSpecs, RecursoModel.getEspecificaciones());
    }
}