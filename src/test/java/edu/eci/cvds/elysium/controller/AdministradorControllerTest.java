// package edu.eci.cvds.elysium.controller;

// import edu.eci.cvds.elysium.service.AdministradorService;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.Mockito.doNothing;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(AdministradorController.class)
// public class AdministradorControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private AdministradorService administradorService;

//     @Test
//     @DisplayName("POST /api/administrador/agregarUsuario - Agrega un usuario")  
//     public void testAgregarUsuario() throws Exception {
//         doNothing().when(administradorService).agregarUsuario(1111, "John", "Doe", "john@mail.com");

//         mockMvc.perform(post("/api/administrador/agregarUsuario")
//                 .param("id", "1111")
//                 .param("nombre", "John")
//                 .param("apellido", "Doe")
//                 .param("correo", "john@mail.com"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     @DisplayName("PUT /api/administrador/actualizarInformacionUsuario - Actualiza la información de un usuario")
//     public void testActualizarInformacionUsuario() throws Exception {
//         doNothing().when(administradorService).actualizarInformacionUsuario(1111, 'n', "Jane");

//         mockMvc.perform(put("/api/administrador/actualizarInformacionUsuario")
//                 .param("id", "1111")
//                 .param("tipoCampo", "n")
//                 .param("value", "Jane"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     @DisplayName("PUT /api/administrador/deshabilitarUsuario - Deshabilita un usuario")
//     public void testDeshabilitarUsuario() throws Exception {
//         doNothing().when(administradorService).deshabilitarUsuario(1111);

//         mockMvc.perform(put("/api/administrador/deshabilitarUsuario")
//                 .param("id", "1111"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     @DisplayName("POST /api/administrador/añadirSalon - Añade un salón")
//     public void testAñadirSalon() throws Exception {
//         doNothing().when(administradorService).añadirSalon(1111, "Salon1", "Location1", 30);

//         mockMvc.perform(post("/api/administrador/añadirSalon")
//                 .param("adminId", "1111")
//                 .param("nombre", "Salon1")
//                 .param("ubicacion", "Location1")
//                 .param("capacidad", "30"))
//                 .andExpect(status().isOk());
//     }
// }
