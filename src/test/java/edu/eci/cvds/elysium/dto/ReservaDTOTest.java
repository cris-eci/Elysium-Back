package edu.eci.cvds.elysium.dto;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaDTOTest {

    @Test
    public void testConversionModelToDTOAndBack() {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel modelo = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        modelo.setIdReserva("RES-001");
        modelo.setEstado(EstadoReservaModel.ACTIVA);

        // Convertir modelo a DTO
        ReservaDTO dto = ReservaDTO.fromModel(modelo);
        assertEquals("RES-001", dto.getIdReserva());
        assertEquals(fecha, dto.getFechaReserva());
        assertEquals("LUNES", dto.getDiaSemana());
        assertEquals("Clase de Programación", dto.getProposito());
        assertEquals("SALON-101", dto.getIdSalon());
        assertEquals("ACTIVA", dto.getEstado());
        assertTrue(dto.isDuracionBloque());

        // Convertir DTO de vuelta a modelo
        ReservaModel modelo2 = ReservaDTO.toModel(dto);
        assertEquals("RES-001", modelo2.getIdReserva());
        assertEquals(fecha, modelo2.getFechaReserva());
        assertEquals(DiaSemanaModel.LUNES, modelo2.getDiaSemana());
        assertEquals("Clase de Programación", modelo2.getProposito());
        assertEquals("SALON-101", modelo2.getIdSalon());
        assertEquals(EstadoReservaModel.ACTIVA, modelo2.getEstado());
        assertTrue(modelo2.isDuracionBloque());
    }
}
