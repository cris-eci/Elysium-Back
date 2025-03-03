package edu.eci.cvds.elysium.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ReservaModelTest {

    @Test
    public void testConstructorYGetters() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel reserva = new ReservaModel("RES-001", fecha, DiaSemanaModel.LUNES, "Clase de Programación", new SalonModel("SALON-101"), true);
            
        assertEquals("RES-001", reserva.getIdReserva());
        assertEquals(fecha, reserva.getFechaReserva());
        assertEquals(DiaSemanaModel.LUNES, reserva.getDiaSemana());
        assertEquals("Clase de Programación", reserva.getProposito());
        assertNotEquals("SALON-101", reserva.getIdSalon());
        assertTrue(reserva.isDuracionBloque());
    }

    @Test
    public void testSetters() {
        ReservaModel reserva = new ReservaModel();
        LocalDate fecha = LocalDate.of(2025, 3, 11);
        reserva.setFechaReserva(fecha);
        reserva.setDiaSemana(DiaSemanaModel.MARTES);
        reserva.setProposito("Actualización");
        reserva.setIdSalon(new SalonModel("SALON-102"));
        reserva.setDuracionBloque(false);
        reserva.setIdReserva("RES-001");
        reserva.setEstado(EstadoReservaModel.ACTIVA);
            
        assertEquals("RES-001", reserva.getIdReserva());
        assertEquals(fecha, reserva.getFechaReserva());
        assertEquals(DiaSemanaModel.MARTES, reserva.getDiaSemana());
        assertEquals("Actualización", reserva.getProposito());
        assertNotEquals("SALON-102", reserva.getIdSalon());
        assertEquals(EstadoReservaModel.ACTIVA, reserva.getEstado());
        assertFalse(reserva.isDuracionBloque());
    }

    @Test
    public void testCrearReserva() {
        LocalDate fecha = LocalDate.of(2025, 3, 12);
        ReservaModel reserva = new ReservaModel();
        ReservaModel nuevaReserva = reserva.crearReserva("RES-002", fecha, DiaSemanaModel.MIERCOLES, "Reunión", new SalonModel("SALON-103"), true);
            
        assertEquals("RES-002", nuevaReserva.getIdReserva());
        assertEquals(fecha, nuevaReserva.getFechaReserva());
        assertEquals(DiaSemanaModel.MIERCOLES, nuevaReserva.getDiaSemana());
        assertEquals("Reunión", nuevaReserva.getProposito());
        assertNotEquals("SALON-103", nuevaReserva.getIdSalon());
        assertTrue(nuevaReserva.isDuracionBloque());
        assertNotEquals(EstadoReservaModel.ACTIVA, nuevaReserva.getEstado());
    }

    @Test
    public void testActualizar() {
        LocalDate fecha = LocalDate.of(2025, 3, 13);
        ReservaModel reserva = new ReservaModel("RES-003", fecha, DiaSemanaModel.JUEVES, "Conferencia", new SalonModel("SALON-104"), true);
        reserva.actualizar("RES-003", 'f', LocalDate.of(2025, 3, 14), null, null, false);
            
        assertEquals(LocalDate.of(2025, 3, 14), reserva.getFechaReserva());
        assertEquals(DiaSemanaModel.JUEVES, reserva.getDiaSemana());
        assertEquals("Conferencia", reserva.getProposito());
        assertNotEquals("SALON-104", reserva.getIdSalon());
        assertTrue(reserva.isDuracionBloque());
        assertEquals(EstadoReservaModel.ACTIVA, reserva.getEstado());
    }

    @Test
    public void testDeleteReserva() {
        ReservaModel reserva = new ReservaModel("RES-004", LocalDate.of(2025, 3, 15), DiaSemanaModel.VIERNES, "Evento", new SalonModel("SALON-105"), true);
        reserva.deleteReserva("RES-004");
            
        assertEquals(EstadoReservaModel.ELIMINADA, reserva.getEstado());
    }

    @Test
    public void testCancelReserva() {
        ReservaModel reserva = new ReservaModel("RES-005", LocalDate.of(2025, 3, 16), DiaSemanaModel.SABADO, "Taller", new SalonModel("SALON-106"), true);
        reserva.cancelReserva("RES-005");
            
        assertEquals(EstadoReservaModel.CANCELADA, reserva.getEstado());
    }

    @Test
    public void testRechazarReserva() {
        ReservaModel reserva = new ReservaModel("RES-006", LocalDate.of(2025, 3, 17), DiaSemanaModel.SABADO, "Seminario", new SalonModel("SALON-107"), true);
        reserva.rechazarReserva("RES-006");
            
        assertEquals(EstadoReservaModel.RECHAZADA, reserva.getEstado());
    }
}

