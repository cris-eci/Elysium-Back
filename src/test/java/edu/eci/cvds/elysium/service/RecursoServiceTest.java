package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.RecursoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class RecursoServiceTest {

    @Mock
    private RecursoService recursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarRecursos() {
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel(), new RecursoModel());
        when(recursoService.consultarRecursos()).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarRecursos();
        assertEquals(2, result.size());
        verify(recursoService, times(1)).consultarRecursos();
    }

    @Test
    public void testConsultarNombre() {
        String nombre = "Recurso1";
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
        when(recursoService.consultarNombre(nombre)).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarNombre(nombre);
        assertEquals(1, result.size());
        verify(recursoService, times(1)).consultarNombre(nombre);
    }

    @Test
    public void testConsultarCantidad() {
        int cantidad = 10;
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
        when(recursoService.consultarCantidad(cantidad)).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarCantidad(cantidad);
        assertEquals(1, result.size());
        verify(recursoService, times(1)).consultarCantidad(cantidad);
    }

    @Test
    public void testConsultarEspecificaciones() {
        List<String> especificaciones = Arrays.asList("Especificacion1", "Especificacion2");
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
        when(recursoService.consultarEspecificaciones(especificaciones)).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarEspecificaciones(especificaciones);
        assertEquals(1, result.size());
        verify(recursoService, times(1)).consultarEspecificaciones(especificaciones);
    }

    @Test
    public void testConsultarRecurso() {
        UUID id = UUID.randomUUID();
        RecursoModel recurso = new RecursoModel();
        when(recursoService.consultarRecurso(id)).thenReturn(recurso);

        RecursoModel result = recursoService.consultarRecurso(id);
        assertNotNull(result);
        verify(recursoService, times(1)).consultarRecurso(id);
    }

    @Test
    public void testAgregarRecurso() {
        String nombre = "Recurso1";
        int cantidad = 10;
        List<String> especificaciones = Arrays.asList("Especificacion1", "Especificacion2");

        doNothing().when(recursoService).agregarRecurso(nombre, cantidad, especificaciones);

        recursoService.agregarRecurso(nombre, cantidad, especificaciones);
        verify(recursoService, times(1)).agregarRecurso(nombre, cantidad, especificaciones);
    }

    @Test
    public void testActualizarRecurso() {
        String nombre = "Recurso1";
        int cantidad = 10;
        List<String> especificaciones = Arrays.asList("Especificacion1", "Especificacion2");

        doNothing().when(recursoService).actualizarRecurso(nombre, cantidad, especificaciones);

        recursoService.actualizarRecurso(nombre, cantidad, especificaciones);
        verify(recursoService, times(1)).actualizarRecurso(nombre, cantidad, especificaciones);
    }

    @Test
    public void testEliminarRecurso() {
        UUID id = UUID.randomUUID();

        doNothing().when(recursoService).eliminarRecurso(id);

        recursoService.eliminarRecurso(id);
        verify(recursoService, times(1)).eliminarRecurso(id);
    }
}