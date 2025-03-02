package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    private ReservaRepository reservaRepository;
    private ReservaService reservaService;

    @BeforeEach
    public void setUp() {
        reservaRepository = Mockito.mock(ReservaRepository.class);
        reservaService = new ReservaService(reservaRepository);
    }

    @Test
    public void testCrearReservaConDatosValidos() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel reserva = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);

        when(reservaRepository.save(any(ReservaModel.class))).thenAnswer(invocation -> {
            ReservaModel r = invocation.getArgument(0);
            r.setIdReserva("RES-001");
            return r;
        });

        ReservaModel creada = reservaService.crearReserva(reserva);
        assertNotNull(creada.getIdReserva());
        assertEquals("ACTIVA", creada.getEstado().name());
    }

    @Test
    public void testActualizarReserva() {
        LocalDate fecha1 = LocalDate.of(2025, 3, 10);
        LocalDate fecha2 = LocalDate.of(2025, 3, 11);
        ReservaModel existente = new ReservaModel(fecha1, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        existente.setIdReserva("RES-001");
        existente.setEstado(EstadoReservaModel.ACTIVA);

        when(reservaRepository.findById("RES-001")).thenReturn(Optional.of(existente));
        when(reservaRepository.save(any(ReservaModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ReservaModel actualizacion = new ReservaModel(fecha2, DiaSemanaModel.MARTES, "Actualización", "SALON-102", false);
        ReservaModel actualizada = reservaService.actualizarReserva("RES-001", actualizacion);
        assertEquals(fecha2, actualizada.getFechaReserva());
        assertEquals(DiaSemanaModel.MARTES, actualizada.getDiaSemana());
        assertNotEquals("Actualización", actualizada.getProposito());
        assertEquals("SALON-102", actualizada.getIdSalon());
        assertFalse(actualizada.isDuracionBloque());
        assertEquals("ACTIVA", actualizada.getEstado().name());
    }

    @Test
    public void testCancelarReserva() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel existente = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        existente.setIdReserva("RES-001");
        existente.setEstado(EstadoReservaModel.ACTIVA);

        when(reservaRepository.findById("RES-001")).thenReturn(Optional.of(existente));
        when(reservaRepository.save(any(ReservaModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ReservaModel cancelada = reservaService.cancelarReserva("RES-001", LocalTime.of(9, 0));
        assertEquals("CANCELADA", cancelada.getEstado().name());
    }

    @Test
    public void testEliminarReservaConAdmin() {
        ReservaModel existente = new ReservaModel(LocalDate.of(2025, 3, 10), DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        existente.setIdReserva("RES-001");
        existente.setEstado(EstadoReservaModel.ACTIVA);

        when(reservaRepository.findById("RES-001")).thenReturn(Optional.of(existente));
        when(reservaRepository.save(any(ReservaModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean eliminado = reservaService.eliminarReserva("RES-001");
        assertTrue(eliminado);
        // Simula que el estado pasa a ELIMINADA
        verify(reservaRepository).save(argThat(r -> r.getEstado() == EstadoReservaModel.ELIMINADA));
    }

}
