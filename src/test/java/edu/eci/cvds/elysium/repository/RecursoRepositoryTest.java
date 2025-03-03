package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.RecursoModel;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;






@ExtendWith(MockitoExtension.class)
public class RecursoRepositoryTest {

    @Mock
    private RecursoRepository recursoRepository;

    private RecursoModel recurso1;
    private RecursoModel recurso2;

    @BeforeEach
    public void setUp() {
        recurso1 = new RecursoModel();
        recurso1.setId(new ObjectId());
        recurso1.setNombre("Recurso1");
        recurso1.setCantidad(10);
        recurso1.setEspecificaciones(Arrays.asList("Spec1", "Spec2"));

        recurso2 = new RecursoModel();
        recurso2.setId(new ObjectId());
        recurso2.setNombre("Recurso2");
        recurso2.setCantidad(5);
        recurso2.setEspecificaciones(Arrays.asList("Spec3", "Spec4"));
    }

    @Test
    public void testFindAll() {
        when(recursoRepository.findAll()).thenReturn(Arrays.asList(recurso1, recurso2));

        List<RecursoModel> recursos = recursoRepository.findAll();
        assertEquals(2, recursos.size());
        verify(recursoRepository, times(1)).findAll();
    }

    @Test
    public void testFindByNombre() {
        when(recursoRepository.findByNombre("Recurso1")).thenReturn(Arrays.asList(recurso1));

        List<RecursoModel> recursos = recursoRepository.findByNombre("Recurso1");
        assertEquals(1, recursos.size());
        assertEquals("Recurso1", recursos.get(0).getNombre());
        verify(recursoRepository, times(1)).findByNombre("Recurso1");
    }

    @Test
    public void testFindByCantidad() {
        when(recursoRepository.findByCantidad(10)).thenReturn(Arrays.asList(recurso1));

        List<RecursoModel> recursos = recursoRepository.findByCantidad(10);
        assertEquals(1, recursos.size());
        assertEquals(10, recursos.get(0).getCantidad());
        verify(recursoRepository, times(1)).findByCantidad(10);
    }

    @Test
    public void testFindByEspecificaciones() {
        when(recursoRepository.findByEspecificaciones(Arrays.asList("Spec1", "Spec2"))).thenReturn(Arrays.asList(recurso1));

        List<RecursoModel> recursos = recursoRepository.findByEspecificaciones(Arrays.asList("Spec1", "Spec2"));
        assertEquals(1, recursos.size());
        assertEquals(Arrays.asList("Spec1", "Spec2"), recursos.get(0).getEspecificaciones());
        verify(recursoRepository, times(1)).findByEspecificaciones(Arrays.asList("Spec1", "Spec2"));
    }

    @Test
    public void testFindById() {
        UUID id = UUID.randomUUID();
        when(recursoRepository.findById(id)).thenReturn(recurso1);

        RecursoModel recurso = recursoRepository.findById(id);
        assertNotEquals(id, recurso.getId());
        verify(recursoRepository, times(1)).findById(id);
    }
}