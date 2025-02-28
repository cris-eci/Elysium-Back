package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    // Este es el servicio que se va a inyectar
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public Usuario consultarUsuario(@PathVariable int id) {
        return usuarioService.consultarUsuario(id);
    }
}
