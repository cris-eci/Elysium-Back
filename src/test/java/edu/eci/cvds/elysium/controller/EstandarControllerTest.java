package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.model.Estandar;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.service.EstandarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstandarController.class)
public class EstandarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstandarService estandarService;

    @Test
    @DisplayName("POST /api/estandar/crearReserva - Crea una reserva")
    public void testCrearReserva() throws Exception {
        int id = 7777;
        String fechaInicio = "12:00";
        String proposito = "Reunion";
        String mnemonico = "XYZ789";
        LocalTime time = LocalTime.parse(fechaInicio);
        Reserva reserva = new Reserva(time, proposito, mnemonico, new Estandar(id, "Test", "User", "test@mail.com", true));
        
        when(estandarService.crearReserva(id, time, proposito, mnemonico)).thenReturn(reserva);

        mockMvc.perform(post("/api/estandar/crearReserva")
                .param("id", String.valueOf(id))
                .param("fechaInicio", fechaInicio)
                .param("proposito", proposito)
                .param("mnemonico", mnemonico))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.proposito").value(proposito))
                .andExpect(jsonPath("$.mnemonico").value(mnemonico));
    }
}
