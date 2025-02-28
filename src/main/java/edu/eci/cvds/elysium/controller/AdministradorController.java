package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/agregarUsuario")
    public void agregarUsuario(@RequestParam int id, @RequestParam String nombre,
                               @RequestParam String apellido, @RequestParam String correo) {
        administradorService.agregarUsuario(id, nombre, apellido, correo);
    }

    @PutMapping("/actualizarInformacionUsuario")
    public void actualizarInformacionUsuario(@RequestParam int id,
                                             @RequestParam char tipoCampo,
                                             @RequestParam String value) {
        administradorService.actualizarInformacionUsuario(id, tipoCampo, value);
    }

    @PutMapping("/deshabilitarUsuario")
    public void deshabilitarUsuario(@RequestParam int id) {
        administradorService.deshabilitarUsuario(id);
    }

    @PostMapping("/añadirSalon")
    public void añadirSalon(@RequestParam int adminId,
                            @RequestParam String nombre,
                            @RequestParam String ubicacion,
                            @RequestParam int capacidad) {
        administradorService.añadirSalon(adminId, nombre, ubicacion, capacidad);
    }
}
