package edu.eci.cvds.elysium.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.SalonModel;

public class ReservaDTOTest {

    @Test
    public void testDefaultConstructor() {
        ReservaDTO reserva = new ReservaDTO();
        assertEquals(null, reserva.getIdReserva());
        assertEquals(null, reserva.getFechaReserva());
        assertEquals(null, reserva.getDiaSemana());
        assertEquals(null, reserva.getProposito());
        assertEquals(null, reserva.getIdSalon());
        assertEquals(false, reserva.isDuracionBloque());
    }

    @Test
    public void testParameterizedConstructor() {
        String idReserva = "123";
        LocalDate fechaReserva = LocalDate.now();
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        String proposito = "Meeting";
        SalonModel idSalon = new SalonModel();
        boolean duracionBloque = true;

        ReservaDTO reserva = new ReservaDTO(idReserva, fechaReserva, diaSemana, proposito, idSalon, duracionBloque);

        assertEquals(idReserva, reserva.getIdReserva());
        assertEquals(fechaReserva, reserva.getFechaReserva());
        assertEquals(diaSemana, reserva.getDiaSemana());
        assertEquals(proposito, reserva.getProposito());
        assertEquals(idSalon, reserva.getIdSalon());
        assertTrue(reserva.isDuracionBloque());
    }
}