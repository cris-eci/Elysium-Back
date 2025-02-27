package edu.eci.cvds.elysium.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

class ReservaModelTest {

    @Test
    void testSettersAndGetters() {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-001");
        reserva.setFechaReserva(LocalDate.of(2025, 3, 10));
        reserva.setHoraInicio(LocalTime.of(10, 0));
        reserva.setHoraFin(LocalTime.of(12, 0));
        reserva.setDiaSemana(DiaSemanaModel.LUNES);
        reserva.setProposito("Clase de Programación");
        reserva.setIdLaboratorio("LAB-101");
        reserva.setIdUsuario("USER-001");
        reserva.setEstado(EstadoReservaModel.RESERVADA);

        assertEquals("RES-001", reserva.getIdReserva());
        assertEquals(LocalDate.of(2025, 3, 10), reserva.getFechaReserva());
        assertEquals(LocalTime.of(10, 0), reserva.getHoraInicio());
        assertEquals(LocalTime.of(12, 0), reserva.getHoraFin());
        assertEquals(DiaSemanaModel.LUNES, reserva.getDiaSemana());
        assertEquals("Clase de Programación", reserva.getProposito());
        assertEquals("LAB-101", reserva.getIdLaboratorio());
        assertEquals("USER-001", reserva.getIdUsuario());
        assertEquals(EstadoReservaModel.RESERVADA, reserva.getEstado());
    }
}
