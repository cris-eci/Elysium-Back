package edu.eci.cvds.elysium.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.service.RecursoService;

import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecursoController.class)
class RecursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecursoService recursoService;

    @Test
    void testCrearRecurso_datosValidos_debeRetornar201() throws Exception {
        RecursoModel recurso = new RecursoModel("REC-001", "Computadores MAC", 10,
                Arrays.asList("Sistema: macOS", "Almacenamiento: 256GB"));
        when(recursoService.crearRecurso(any(RecursoModel.class))).thenReturn(recurso);

        String json = """
            {
                "nombre": "Computadores MAC",
                "cantidad": 10,
                "especificaciones": ["Sistema: macOS", "Almacenamiento: 256GB"]
            }
            """;

        mockMvc.perform(post("/recursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("REC-001"))
                .andExpect(jsonPath("$.nombre").value("Computadores MAC"));
    }

    @Test
    void testActualizarRecurso_comoAdmin_debeRetornar200() throws Exception {
        RecursoModel recursoActualizado = new RecursoModel("REC-002", "Computadores Windows", 15,
                Arrays.asList("Sistema: Windows", "Almacenamiento: 512GB"));
        when(recursoService.actualizarRecurso(eq("REC-002"), any(RecursoModel.class), eq(true)))
                .thenReturn(recursoActualizado);

        String json = """
            {
                "nombre": "Computadores Windows",
                "cantidad": 15,
                "especificaciones": ["Sistema: Windows", "Almacenamiento: 512GB"]
            }
            """;

        mockMvc.perform(put("/recursos/REC-002")
                .header("X-Role", "ADMIN")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Computadores Windows"))
                .andExpect(jsonPath("$.cantidad").value(15));
    }

    @Test
    void testActualizarRecurso_sinPermiso_debeRetornar403() throws Exception {
        when(recursoService.actualizarRecurso(eq("REC-003"), any(RecursoModel.class), eq(false)))
                .thenThrow(new SecurityException("No tiene permisos para actualizar el recurso"));

        String json = """
            {
                "nombre": "Computadores Windows",
                "cantidad": 15,
                "especificaciones": ["Sistema: Windows", "Almacenamiento: 512GB"]
            }
            """;

        mockMvc.perform(put("/recursos/REC-003")
                .header("X-Role", "USER")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isForbidden());
    }

    @Test
    void testEliminarRecurso_comoAdmin_debeRetornar200() throws Exception {
        when(recursoService.eliminarRecurso("REC-004", true)).thenReturn(true);

        mockMvc.perform(delete("/recursos/REC-004")
                .header("X-Role", "ADMIN"))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarRecurso_sinPermiso_debeRetornar403() throws Exception {
        when(recursoService.eliminarRecurso("REC-005", false))
                .thenThrow(new SecurityException("No tiene permisos para eliminar el recurso"));

        mockMvc.perform(delete("/recursos/REC-005")
                .header("X-Role", "USER"))
                .andExpect(status().isForbidden());
    }
}
