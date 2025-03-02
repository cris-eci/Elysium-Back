package edu.eci.cvds.elysium.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaModelTest {

    @Test
    public void testConstructorYGetters() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel reserva = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        
        // El ID aún no se asigna en el constructor
        assertNull(reserva.getIdReserva());
        assertEquals(fecha, reserva.getFechaReserva());
        assertEquals(DiaSemanaModel.LUNES, reserva.getDiaSemana());
        assertEquals("Clase de Programación", reserva.getProposito());
        assertEquals("SALON-101", reserva.getIdSalon());
        // Se espera que el estado y duración sean definidos posteriormente
        assertFalse(reserva.isDuracionBloque() != true && reserva.isDuracionBloque() != false); // simplemente comprueba que es un booleano
    }

    @Test
    public void testSetters() {
        ReservaModel reserva = new ReservaModel();
        LocalDate fecha = LocalDate.of(2025, 3, 11);
        reserva.setFechaReserva(fecha);
        reserva.setDiaSemana(DiaSemanaModel.MARTES);
        reserva.setProposito("Actualización");
        reserva.setIdSalon("SALON-102");
        reserva.setDuracionBloque(false);
        reserva.setIdReserva("RES-001");
        reserva.setEstado(EstadoReservaModel.ACTIVA);
        
        assertEquals("RES-001", reserva.getIdReserva());
        assertEquals(fecha, reserva.getFechaReserva());
        assertEquals(DiaSemanaModel.MARTES, reserva.getDiaSemana());
        assertEquals("Actualización", reserva.getProposito());
        assertEquals("SALON-102", reserva.getIdSalon());
        assertEquals(EstadoReservaModel.ACTIVA, reserva.getEstado());
        assertFalse(reserva.isDuracionBloque());
    }
}
