// package edu.eci.cvds.elysium.service;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// public class UsuarioServiceTest {

//     @Autowired
//     private UsuarioService usuarioService;

//     private Usuario testUsuario;

//     @BeforeEach
//     public void setUp(){
//         // Se crea un usuario de prueba utilizando el constructor parametrizado
//         testUsuario = new Usuario(1000098751, "Andres", "Cicerón", "andres.ciceron-n@mail.escuelaing.edu.co", true);
        
//         // Se guarda el usuario y se actualiza testUsuario con el objeto retornado (que incluye el ID generado)
//         testUsuario = usuarioService.createUser(testUsuario);
//     }

//     @AfterEach
//     public void tearDown() {
//         // Se elimina el usuario de prueba, siempre y cuando tenga asignado un ID
//         if (testUsuario != null && testUsuario.getId() != null) {
//             usuarioService.deleteUserById(testUsuario.getId());
//         }
//     }

//     @Test
//     public void testCreateUser() {
//         // Crear un nuevo usuario para probar la operación de creación usando el nuevo constructor
//         Usuario newUser = new Usuario(1000098752, "Maria", "Perez", "maria.perez@mail.com", true);
//         Usuario createdUser = usuarioService.createUser(newUser);
//         assertNotNull(createdUser.getId(), "El usuario creado debe tener un ID asignado");
//         assertEquals("Maria", createdUser.getNombre(), "El nombre del usuario debe coincidir");

//         // Limpieza: se elimina el usuario creado en este test
//         usuarioService.deleteUserById(createdUser.getId());
//     }

//     @Test
//     public void testGetUserById() {
//         // Se recupera el usuario de prueba por su ID
//         Usuario foundUser = usuarioService.getUserById(testUsuario.getId());
//         assertNotNull(foundUser, "El usuario debería existir");
//         assertEquals(testUsuario.getNombre(), foundUser.getNombre(), "El nombre del usuario debe coincidir");
//     }

//     @Test
//     public void testGetAllUsers() {
//         // Se obtiene la lista de todos los usuarios
//         List<Usuario> usuarios = usuarioService.getAllUsers();
//         assertTrue(usuarios.size() > 0, "Debe haber al menos un usuario en la lista");

//         // Se verifica que el usuario de prueba esté presente en la lista
//         boolean exists = usuarios.stream().anyMatch(u -> u.getId().equals(testUsuario.getId()));
//         assertTrue(exists, "El usuario de prueba debe estar en la lista");
//     }

//     @Test
//     public void testUpdateUser() {
//         // Se actualiza el email del usuario de prueba
//         testUsuario.setEmail("andres.actualizado@mail.com");
//         Usuario updatedUser = usuarioService.updateUser(testUsuario);
//         assertEquals("andres.actualizado@mail.com", updatedUser.getEmail(), "El email del usuario debe actualizarse");
//     }

//     @Test
//     public void testDeleteUser() {
//         // Se crea un usuario específico para probar la eliminación usando el nuevo constructor
//         Usuario userToDelete = new Usuario(1000098753, "Juan", "Gomez", "juan.gomez@mail.com", true);
//         userToDelete = usuarioService.createUser(userToDelete);

//         // Se elimina el usuario
//         usuarioService.deleteUserById(userToDelete.getId());

//         // Se verifica que no se pueda recuperar el usuario eliminado
//         Usuario deletedUser = usuarioService.getUserById(userToDelete.getId());
//         assertNull(deletedUser, "El usuario debería haber sido eliminado");
//     }
// }
