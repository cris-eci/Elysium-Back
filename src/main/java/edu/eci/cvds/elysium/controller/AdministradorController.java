package edu.eci.cvds.elysium.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/consultarUsuariosActivos")
    public List<Usuario> consultarUsuariosActivos() {
        return administradorService.consultarUsuariosActivos();
    }

    @GetMapping("/consultarUsuariosInactivos")
    public List<Usuario> findAllInactive() {
        return administradorService.findAllInactive();
    }

    @GetMapping("/consultarAdmins")
    public List<Usuario> findAllAdmins() {
        return administradorService.findAllAdmins();
    }

    @GetMapping("/consultarAdminsActivos")
    public List<Usuario> findAllActiveAdmins() {
        return administradorService.findAllActiveAdmins();
    }

    @GetMapping("/consultarAdminsInactivos")
    public List<Usuario> findAllInactiveAdmins() {
        return administradorService.findAllInactiveAdmins();
    }

    @GetMapping("/consultarNoAdminsActivos")
    public List<Usuario> findAllActiveNoAdmins() {
        return administradorService.findAllActiveNoAdmins();
    }

    @GetMapping("/consultarNoAdminsInactivos")
    public List<Usuario> findAllInactiveNoAdmins() {
        return administradorService.findAllInactiveNoAdmins();
    }

    @PostMapping("/agregarUsuario")
    public void agregarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        administradorService.agregarUsuario(usuarioDTO.getId(), usuarioDTO.getNombre(),
                usuarioDTO.getApellido(), usuarioDTO.getCorreo(), usuarioDTO.getIsAdmin());
    }

    @PutMapping("/actualizarInformacionUsuario")
    public void actualizarInformacionUsuario(@RequestBody ActualizarUsuarioDTO actualizarUsuario) {
        administradorService.actualizarInformacionUsuario(actualizarUsuario.getIdInstitucional(), actualizarUsuario.getTipoCampo(), actualizarUsuario.getValue());
    }

    @PutMapping("{id}/deshabilitarUsuario")
    public ResponseEntity<String> deshabilitarUsuario(@PathVariable int id) {
        administradorService.deshabilitarUsuario(id);
        return ResponseEntity.ok("Usuario deshabilitado exitosamente");
    }

    @PutMapping("{id}/habilitarUsuario")
    public ResponseEntity<String> habilitarUsuario(@PathVariable int id) {
        administradorService.habilitarUsuario(id);
        return ResponseEntity.ok("Usuario habilitado exitosamente");
    }

    @PutMapping("{id}/hacerAdmin")
    public ResponseEntity<String> hacerAdmin(@PathVariable int id) {
        administradorService.hacerAdmin(id);
        return ResponseEntity.ok("Usuario ahora es administrador");
    }   


    // ----------
    @PostMapping("/añadirSalon")
    public void añadirSalon(@RequestParam int adminId,
            @RequestParam String nombre,
            @RequestParam String ubicacion,
            @RequestParam int capacidad) {
        administradorService.añadirSalon(adminId, nombre, ubicacion, capacidad);
    }
}
