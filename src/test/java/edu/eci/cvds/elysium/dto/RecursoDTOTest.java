package edu.eci.cvds.elysium.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;


public class RecursoDTOTest {

    @Test
    public void testDefaultConstructor() {
        RecursoDTO recurso = new RecursoDTO();
        assertNull(recurso.getNombre());
        assertEquals(0, recurso.getCantidad());
        assertNull(recurso.getEspecificaciones());
    }

    @Test
    public void testParameterizedConstructor() {
        List<String> especificaciones = Arrays.asList("Spec1", "Spec2");
        RecursoDTO recurso = new RecursoDTO("Recurso1", 10, especificaciones);

        assertEquals("Recurso1", recurso.getNombre());
        assertEquals(10, recurso.getCantidad());
        assertEquals(especificaciones, recurso.getEspecificaciones());
    }

    @Test
    public void testGetNombre() {
        RecursoDTO recurso = new RecursoDTO("Recurso1", 10, null);
        assertEquals("Recurso1", recurso.getNombre());
    }

    @Test
    public void testGetCantidad() {
        RecursoDTO recurso = new RecursoDTO("Recurso1", 10, null);
        assertEquals(10, recurso.getCantidad());
    }

    @Test
    public void testGetEspecificaciones() {
        List<String> especificaciones = Arrays.asList("Spec1", "Spec2");
        RecursoDTO recurso = new RecursoDTO("Recurso1", 10, especificaciones);
        assertEquals(especificaciones, recurso.getEspecificaciones());
    }
}