package edu.eci.cvds.elysium.service;

import java.util.List;

import edu.eci.cvds.elysium.model.Usuario;

public interface UsuarioService {
    Usuario createUser(Usuario usuario);
    Usuario consultarUsuario(int idInstitucional);
    List<Usuario> consultarUsuarios();
    List<Usuario> consultarUsuariosActivos();
    List<Usuario> findAllInactive();
    List<Usuario> findAllAdmins();
    List<Usuario> findAllActiveAdmins();
    List<Usuario> findAllInactiveAdmins();
    List<Usuario> findAllActiveNoAdmins();
    List<Usuario> findAllInactiveNoAdmins();
    //void deleteUser(int idInstitucional);
}
