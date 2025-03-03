package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.model.SalonModel;
import edu.eci.cvds.elysium.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.nullValue;



public class ReservaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaController).build();
    }

    @Test
    public void testConsultarReservas() throws Exception {
        when(reservaService.consultarReservas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reserva/consultarReservas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testConsultarReservasPorSalon() throws Exception {
        SalonModel salon = new SalonModel();
        when(reservaService.consultarReservasPorSalon(salon)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reserva/consultarReservasPorSalon")
                .param("idSalon", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testConsultarReservasPorFecha() throws Exception {
        LocalDate fecha = LocalDate.now();
        when(reservaService.consultarReservasPorFecha(fecha)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reserva/consultarReservasPorFecha")
                .param("fecha", fecha.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testConsultarReservasPorDiaSemana() throws Exception {
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        when(reservaService.consultarReservasPorDiaSemana(diaSemana)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reserva/consultarReservasPorDiaSemana")
                .param("diaSemana", diaSemana.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testConsultarReservasPorEstado() throws Exception {
        EstadoReservaModel estado = EstadoReservaModel.ACTIVA;
        when(reservaService.consultarReservasPorEstado(estado)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reserva/consultarReservasPorEstado")
                .param("estado", estado.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testConsultarReservasPorDuracionBloque() throws Exception {
        when(reservaService.consultarReservasPorDuracionBloque(true)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reserva/consultarReservasPorDuracionBloque")
                .param("duracionBloque", "true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testConsultarReserva() throws Exception {
        String idReserva = "1";
        ReservaModel reserva = new ReservaModel();
        when(reservaService.consultarReserva(idReserva)).thenReturn(reserva);

        mockMvc.perform(get("/api/reserva/consultarReserva")
                .param("idReserva", idReserva))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idReserva", nullValue()));
    }


    @Test
    public void testCrearReserva() throws Exception {
        mockMvc.perform(post("/api/reserva/crearReserva")
                .param("idReserva", "1")
                .param("fechaReserva", LocalDate.now().toString())
                .param("diaSemana", DiaSemanaModel.LUNES.toString())
                .param("proposito", "Meeting")
                .param("salon", "1")
                .param("duracionBloque", "true"))
                .andExpect(status().isOk());
    }

    @Test
    public void testActualizarReserva() throws Exception {
        mockMvc.perform(put("/api/reserva/actualizarReserva")
                .param("idReserva", "1")
                .param("tipoCampo", "A")
                .param("value1", LocalDate.now().toString())
                .param("value2", DiaSemanaModel.LUNES.toString())
                .param("value3", "1")
                .param("value4", "true"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteReserva() throws Exception {
        mockMvc.perform(delete("/api/reserva/deleteReserva")
                .param("idReserva", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelReserva() throws Exception {
        mockMvc.perform(put("/api/reserva/cancelReserva")
                .param("idReserva", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRechazarReserva() throws Exception {
        mockMvc.perform(put("/api/reserva/rechazarReserva")
                .param("idReserva", "1"))
                .andExpect(status().isOk());
    }
}