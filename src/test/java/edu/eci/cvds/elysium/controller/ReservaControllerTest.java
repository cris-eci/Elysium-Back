package edu.eci.cvds.elysium.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearReserva() throws Exception {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaDTO reservaDto = new ReservaDTO(null, fecha, "LUNES", "Clase de Programación", "SALON-101", null, true);

        ReservaModel modelo = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        modelo.setIdReserva("RES-001");
        modelo.setEstado(EstadoReservaModel.ACTIVA);

        when(reservaService.crearReserva(any(ReservaModel.class))).thenReturn(modelo);

        mockMvc.perform(post("/api/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservaDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idReserva").value("RES-001"))
            .andExpect(jsonPath("$.estado").value("ACTIVA"));
    }

    @Test
    public void testActualizarReserva() throws Exception {
        LocalDate fecha = LocalDate.of(2025, 3, 11);
        ReservaDTO reservaDto = new ReservaDTO(null, fecha, "MARTES", "Actualización", "SALON-102", null, false);

        ReservaModel modeloExistente = new ReservaModel(LocalDate.of(2025, 3, 10), DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        modeloExistente.setIdReserva("RES-002");
        modeloExistente.setEstado(EstadoReservaModel.ACTIVA);

        ReservaModel modeloActualizado = new ReservaModel(fecha, DiaSemanaModel.MARTES, "Actualización", "SALON-102", false);
        modeloActualizado.setIdReserva("RES-002");
        modeloActualizado.setEstado(EstadoReservaModel.ACTIVA);

        when(reservaService.actualizarReserva(eq("RES-002"), any(ReservaModel.class))).thenReturn(modeloActualizado);

        mockMvc.perform(put("/api/reservas/RES-002")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservaDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fechaReserva").value(fecha.toString()))
            .andExpect(jsonPath("$.diaSemana").value("MARTES"));
    }

    @Test
    public void testCancelarReserva() throws Exception {
        LocalDate fecha = LocalDate.of(2025, 3, 10);
        ReservaModel modelo = new ReservaModel(fecha, DiaSemanaModel.LUNES, "Clase de Programación", "SALON-101", true);
        modelo.setIdReserva("RES-003");
        modelo.setEstado(EstadoReservaModel.CANCELADA);

        when(reservaService.cancelarReserva(eq("RES-003"), any())).thenReturn(modelo);

        mockMvc.perform(put("/api/reservas/RES-003/cancelar")
                .param("horaActual", "09:00"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.estado").value("CANCELADA"));
    }
}
