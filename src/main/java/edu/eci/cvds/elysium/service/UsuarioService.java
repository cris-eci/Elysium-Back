package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario createUser(Usuario usuario);
    Usuario consultarUsuario(int idInstitucional);
    List<Usuario> consultarUsuarios();
    void deleteUser(int idInstitucional);
}
