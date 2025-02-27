package edu.eci.cvds.elysium.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.service.ReservaService;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @Test
    void testCrearReserva_datosValidos_debeRetornar201() throws Exception {
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

        when(reservaService.crearReserva(any(ReservaModel.class))).thenReturn(reserva);

        String json = """
                {
                    "fechaReserva": "2025-03-10",
                    "horaInicio": "10:00",
                    "horaFin": "12:00",
                    "diaSemana": "LUNES",
                    "proposito": "Clase de Programación",
                    "idLaboratorio": "LAB-101",
                    "idUsuario": "USER-001"
                }
                """;

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idReserva").value("RES-001"))
                .andExpect(jsonPath("$.estado").value("RESERVADA"));
    }

    @Test
    void testCrearReserva_camposFaltantes_debeRetornar400() throws Exception {
        String json = """
                {
                    "fechaReserva": "2025-03-10",
                    "horaInicio": "10:00",
                    "proposito": "Clase de Programación",
                    "idLaboratorio": "LAB-101",
                    "idUsuario": "USER-001"
                }
                """;

        when(reservaService.crearReserva(any(ReservaModel.class)))
                .thenThrow(new IllegalArgumentException("Campos obligatorios faltantes"));

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEliminarReserva_comoAdmin_debeRetornar200() throws Exception {
        when(reservaService.eliminarReserva("RES-100", true)).thenReturn(true);

        mockMvc.perform(delete("/reservas/RES-100")
                .header("X-Role", "ADMIN"))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarReserva_sinPermisos_debeRetornar403() throws Exception {
        when(reservaService.eliminarReserva("RES-101", false))
                .thenThrow(new SecurityException("No tiene permisos para eliminar la reserva"));

        mockMvc.perform(delete("/reservas/RES-101")
                .header("X-Role", "USER"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCancelarReserva_antesDeIniciar_debeRetornar200() throws Exception {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva("RES-200");
        reserva.setEstado(EstadoReservaModel.CANCELADA);
        when(reservaService.cancelarReserva("RES-200", LocalTime.parse("09:59")))
                .thenReturn(reserva);

        mockMvc.perform(put("/reservas/RES-200/cancelar")
                .param("horaActual", "09:59"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CANCELADA"));
    }

    @Test
    void testCancelarReserva_despuesDeIniciar_debeRetornar400() throws Exception {
        when(reservaService.cancelarReserva("RES-201", LocalTime.parse("10:01")))
                .thenThrow(new IllegalStateException("No se puede cancelar porque la reserva ya inició"));

        mockMvc.perform(put("/reservas/RES-201/cancelar")
                .param("horaActual", "10:01"))
                .andExpect(status().isBadRequest());
    }
}
