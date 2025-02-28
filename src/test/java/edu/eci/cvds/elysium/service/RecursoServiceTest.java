package edu.eci.cvds.elysium.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.repository.RecursoRepository;

import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

class RecursoServiceTest {

    @Mock
    private RecursoRepository recursoRepository;

    private RecursoService recursoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recursoService = new RecursoService(recursoRepository);
    }

    @Test
    void testCrearRecursoConDatosValidos() {
        RecursoModel recurso = new RecursoModel(null, "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        // Simula la asignación de ID en el guardado
        when(recursoRepository.save(any(RecursoModel.class))).thenAnswer(invocation -> {
            RecursoModel r = invocation.getArgument(0);
            if (r.getId() == null || r.getId().isEmpty()) {
                r.setId(UUID.randomUUID().toString());
            }
            return r;
        });

        RecursoModel creado = recursoService.crearRecurso(recurso);
        assertNotNull(creado.getId());
        assertEquals("Computadores MAC", creado.getNombre());
    }

    @Test
    void testActualizarRecursoComoAdmin() {
        RecursoModel recursoExistente = new RecursoModel("REC-001", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        when(recursoRepository.findById("REC-001")).thenReturn(Optional.of(recursoExistente));
        when(recursoRepository.save(any(RecursoModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RecursoModel recursoActualizado = new RecursoModel();
        recursoActualizado.actualizar("Computadores Windows", 15,
                Arrays.asList("Sistema: Windows", "Almacenamiento: 512GB"));

        RecursoModel resultado = recursoService.actualizarRecurso("REC-001", recursoActualizado, true);
        assertEquals("Computadores Windows", resultado.getNombre());
        assertEquals(15, resultado.getCantidad());
    }

    @Test
    void testActualizarRecursoSinPermisoLanzaExcepcion() {
        RecursoModel recursoExistente = new RecursoModel("REC-002", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        when(recursoRepository.findById("REC-002")).thenReturn(Optional.of(recursoExistente));

        RecursoModel recursoActualizado = new RecursoModel();
        recursoActualizado.actualizar("Computadores Windows", 15,
                Arrays.asList("Sistema: Windows", "Almacenamiento: 512GB"));

        assertThrows(SecurityException.class, () -> {
            recursoService.actualizarRecurso("REC-002", recursoActualizado, false);
        });
    }

    @Test
    void testEliminarRecursoComoAdmin() {
        RecursoModel recurso = new RecursoModel("REC-003", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        when(recursoRepository.findById("REC-003")).thenReturn(Optional.of(recurso));
        doNothing().when(recursoRepository).delete(recurso);

        boolean eliminado = recursoService.eliminarRecurso("REC-003", true);
        assertTrue(eliminado);
    }

    @Test
    void testEliminarRecursoSinPermisoLanzaExcepcion() {
        RecursoModel recurso = new RecursoModel("REC-004", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        when(recursoRepository.findById("REC-004")).thenReturn(Optional.of(recurso));
        assertThrows(SecurityException.class, () -> {
            recursoService.eliminarRecurso("REC-004", false);
        });
    }
}