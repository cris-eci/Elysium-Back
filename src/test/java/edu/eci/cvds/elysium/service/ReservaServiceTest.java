package edu.eci.cvds.elysium.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.repository.ReservaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceTest {

    private ReservaService reservaService;
    private ReservaRepository fakeRepo;

    @BeforeEach
    void setUp() {
        //fakeRepo = new ReservaRepository();
        reservaService = new ReservaService(fakeRepo);
    }

    @Test
    void testCrearReservaConDatosValidos_debeRetornarObjetoReserva() {
        ReservaModel nuevaReserva = new ReservaModel();
        nuevaReserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        nuevaReserva.setHoraInicio(LocalTime.of(10, 0));
        nuevaReserva.setHoraFin(LocalTime.of(12, 0));
        nuevaReserva.setDiaSemana(DiaSemanaModel.LUNES);
        nuevaReserva.setProposito("Clase de Programación");
        nuevaReserva.setIdLaboratorio("LAB-101");
        nuevaReserva.setIdUsuario("USER-001");

        ReservaModel resultado = reservaService.crearReserva(nuevaReserva);

        assertNotNull(resultado.getIdReserva());
        assertEquals(EstadoReservaModel.RESERVADA, resultado.getEstado());
        assertEquals("LAB-101", resultado.getIdLaboratorio());
        assertEquals("USER-001", resultado.getIdUsuario());
    }

    @Test
    void testCrearReserva_conCamposFaltantes_debeLanzarExcepcion() {
        ReservaModel nuevaReserva = new ReservaModel();
        // Se omiten campos obligatorios (por ejemplo, horaFin y diaSemana)
        nuevaReserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        nuevaReserva.setHoraInicio(LocalTime.of(10, 0));
        nuevaReserva.setProposito("Clase de Programación");
        nuevaReserva.setIdLaboratorio("LAB-101");
        nuevaReserva.setIdUsuario("USER-001");

        assertThrows(IllegalArgumentException.class, () -> {
            reservaService.crearReserva(nuevaReserva);
        });
    }

    @Test
    void testCrearReserva_conSolapamiento_debeLanzarExcepcion() {
        // Crear reserva existente
        ReservaModel reservaExistente = new ReservaModel();
        reservaExistente.setFechaReserva(LocalDate.of(2025, 3, 10));
        reservaExistente.setHoraInicio(LocalTime.of(10, 0));
        reservaExistente.setHoraFin(LocalTime.of(12, 0));
        reservaExistente.setDiaSemana(DiaSemanaModel.LUNES);
        reservaExistente.setProposito("Reserva existente");
        reservaExistente.setIdLaboratorio("LAB-101");
        reservaExistente.setIdUsuario("USER-002");
        fakeRepo.save(reservaExistente);

        // Nueva reserva que se solapa (10:30 - 11:30)
        ReservaModel nuevaReserva = new ReservaModel();
        nuevaReserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        nuevaReserva.setHoraInicio(LocalTime.of(10, 30));
        nuevaReserva.setHoraFin(LocalTime.of(11, 30));
        nuevaReserva.setDiaSemana(DiaSemanaModel.LUNES);
        nuevaReserva.setProposito("Clase de Programación");
        nuevaReserva.setIdLaboratorio("LAB-101");
        nuevaReserva.setIdUsuario("USER-001");

        assertThrows(IllegalStateException.class, () -> {
            reservaService.crearReserva(nuevaReserva);
        });
    }

    @Test
    void testCancelarReserva_antesDeIniciar_debeCambiarEstadoACancelada() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-001");
        reserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        reserva.setHoraInicio(LocalTime.of(10, 0));
        reserva.setHoraFin(LocalTime.of(12, 0));
        reserva.setDiaSemana(DiaSemanaModel.LUNES);
        reserva.setProposito("Reserva por probar");
        reserva.setIdLaboratorio("LAB-101");
        reserva.setIdUsuario("USER-001");
        reserva.setEstado(EstadoReservaModel.RESERVADA);
        fakeRepo.save(reserva);

        ReservaModel resultado = reservaService.cancelarReserva("RES-001", LocalTime.of(9, 59));
        assertEquals(EstadoReservaModel.CANCELADA, resultado.getEstado());
    }

    @Test
    void testCancelarReserva_despuesDeIniciar_debeLanzarExcepcion() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-002");
        reserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        reserva.setHoraInicio(LocalTime.of(10, 0));
        reserva.setHoraFin(LocalTime.of(12, 0));
        reserva.setDiaSemana(DiaSemanaModel.LUNES);
        reserva.setProposito("Reserva por probar");
        reserva.setIdLaboratorio("LAB-101");
        reserva.setIdUsuario("USER-001");
        reserva.setEstado(EstadoReservaModel.RESERVADA);
        fakeRepo.save(reserva);

        assertThrows(IllegalStateException.class, () -> {
            reservaService.cancelarReserva("RES-002", LocalTime.of(10, 1));
        });
    }

    @Test
    void testEliminarReserva_comoAdministrador_debeEliminar() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-003");
        fakeRepo.save(reserva);

        boolean eliminado = reservaService.eliminarReserva("RES-003", true);
        assertTrue(eliminado);
        assertFalse(fakeRepo.findById("RES-003").isPresent());
    }

    @Test
    void testEliminarReserva_sinPermisoAdmin_debeLanzarExcepcion() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-004");
        fakeRepo.save(reserva);

        assertThrows(SecurityException.class, () -> {
            reservaService.eliminarReserva("RES-004", false);
        });
    }
}
