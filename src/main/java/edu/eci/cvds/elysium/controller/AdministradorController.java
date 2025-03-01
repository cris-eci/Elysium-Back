package edu.eci.cvds.elysium.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.ActualizarUsuarioDTO;
import edu.eci.cvds.elysium.dto.UsuarioDTO;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.service.AdministradorService;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController extends UsuarioController{

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/consultarUsuarios")
    public List<Usuario> consultarUsuarios() {
        return administradorService.consultarUsuarios();
    }

    @PostMapping("/agregarUsuario")
    public void agregarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        administradorService.agregarUsuario(usuarioDTO.getId(), usuarioDTO.getNombre(),
                usuarioDTO.getApellido(), usuarioDTO.getCorreo());
    }

    @PutMapping("/actualizarInformacionUsuario")
    public void actualizarInformacionUsuario(@RequestBody ActualizarUsuarioDTO actualizarUsuario) {
        administradorService.actualizarInformacionUsuario(actualizarUsuario.getIdInstitucional(), actualizarUsuario.getTipoCampo(), actualizarUsuario.getValue());
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
