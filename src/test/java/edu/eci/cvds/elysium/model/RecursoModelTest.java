package edu.eci.cvds.elysium.model;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class RecursoModelTest {

    private RecursoModel recurso;

    @BeforeEach
    public void setUp() {
        recurso = new RecursoModel("Test Resource", 10, Arrays.asList("Spec1", "Spec2"));
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Resource", recurso.getNombre());
        assertEquals(10, recurso.getCantidad());
        assertEquals(Arrays.asList("Spec1", "Spec2"), recurso.getEspecificaciones());
    }

    @Test
    public void testGettersAndSetters() {
        ObjectId id = new ObjectId();
        recurso.setId(id);
        assertEquals(id, recurso.getId());

        recurso.setNombre("New Name");
        assertEquals("New Name", recurso.getNombre());

        recurso.setCantidad(20);
        assertEquals(20, recurso.getCantidad());

        List<String> newSpecs = Arrays.asList("NewSpec1", "NewSpec2");
        recurso.setEspecificaciones(newSpecs);
        assertEquals(newSpecs, recurso.getEspecificaciones());
    }

    @Test
    public void testCrearRecurso() {
        RecursoModel newRecurso = recurso.crearRecurso("New Resource", 5, Arrays.asList("SpecA", "SpecB"));
        assertEquals("New Resource", newRecurso.getNombre());
        assertEquals(5, newRecurso.getCantidad());
        assertEquals(Arrays.asList("SpecA", "SpecB"), newRecurso.getEspecificaciones());
    }

    @Test
    public void testActualizar() {
        recurso.actualizar("Updated Resource", 15, Arrays.asList("UpdatedSpec1", "UpdatedSpec2"));
        assertEquals("Updated Resource", recurso.getNombre());
        assertEquals(15, recurso.getCantidad());
        assertEquals(Arrays.asList("UpdatedSpec1", "UpdatedSpec2"), recurso.getEspecificaciones());

        recurso.actualizar(null, null, null);
        assertEquals("Updated Resource", recurso.getNombre());
        assertEquals(15, recurso.getCantidad());
        assertEquals(Arrays.asList("UpdatedSpec1", "UpdatedSpec2"), recurso.getEspecificaciones());
    }

    @Test
    public void testEliminar() {
        recurso.eliminar("dummy");
        assertNull(recurso.getNombre());
        assertEquals(0, recurso.getCantidad());
        assertNull(recurso.getEspecificaciones());
    }

}