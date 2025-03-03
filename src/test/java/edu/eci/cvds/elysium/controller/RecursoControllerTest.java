package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.dto.RecursoDTO;
import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.service.RecursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class RecursoControllerTest {

    @Mock
    private RecursoService recursoService;

    @InjectMocks
    private RecursoController recursoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarRecursos() {
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel(), new RecursoModel());
        when(recursoService.consultarRecursos()).thenReturn(recursos);

        List<RecursoModel> result = recursoController.consultarRecursos();
        assertEquals(recursos, result);
        verify(recursoService, times(1)).consultarRecursos();
    }

    @Test
    public void testConsultarNombre() {
        String nombre = "Recurso1";
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel(), new RecursoModel());
        when(recursoService.consultarNombre(nombre)).thenReturn(recursos);

        List<RecursoModel> result = recursoController.consultarNombre(nombre);
        assertEquals(recursos, result);
        verify(recursoService, times(1)).consultarNombre(nombre);
    }

    @Test
    public void testConsultarCantidad() {
        int cantidad = 10;
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel(), new RecursoModel());
        when(recursoService.consultarCantidad(cantidad)).thenReturn(recursos);

        List<RecursoModel> result = recursoController.consultarCantidad(cantidad);
        assertEquals(recursos, result);
        verify(recursoService, times(1)).consultarCantidad(cantidad);
    }

    @Test
    public void testConsultarEspecificaciones() {
        List<String> especificaciones = Arrays.asList("Especificacion1", "Especificacion2");
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel(), new RecursoModel());
        when(recursoService.consultarEspecificaciones(especificaciones)).thenReturn(recursos);

        List<RecursoModel> result = recursoController.consultarEspecificaciones(especificaciones);
        assertEquals(recursos, result);
        verify(recursoService, times(1)).consultarEspecificaciones(especificaciones);
    }

    @Test
    public void testConsultarRecurso() {
        UUID id = UUID.randomUUID();
        RecursoModel recurso = new RecursoModel();
        when(recursoService.consultarRecurso(id)).thenReturn(recurso);

        RecursoModel result = recursoController.consultarRecurso(id);
        assertEquals(recurso, result);
        verify(recursoService, times(1)).consultarRecurso(id);
    }

    @Test
    public void testAgregarRecurso() {
        RecursoDTO recursoDTO = new RecursoDTO("Recurso1", 10, Arrays.asList("Especificacion1", "Especificacion2"));

        recursoController.agregarRecurso(recursoDTO);
        verify(recursoService, times(1)).agregarRecurso(recursoDTO.getNombre(), recursoDTO.getCantidad(), recursoDTO.getEspecificaciones());
    }

    @Test
    public void testActualizarRecurso() {
        RecursoDTO recursoDTO = new RecursoDTO("Recurso1", 10, Arrays.asList("Especificacion1", "Especificacion2"));

        recursoController.actualizarRecurso(recursoDTO);
        verify(recursoService, times(1)).actualizarRecurso(recursoDTO.getNombre(), recursoDTO.getCantidad(), recursoDTO.getEspecificaciones());
    }

    @Test
    public void testEliminarRecurso() {
        UUID id = UUID.randomUUID();

        ResponseEntity<String> response = recursoController.eliminarRecurso(id);
        assertEquals(ResponseEntity.ok("Recurso eliminado"), response);
        verify(recursoService, times(1)).eliminarRecurso(id);
    }
}