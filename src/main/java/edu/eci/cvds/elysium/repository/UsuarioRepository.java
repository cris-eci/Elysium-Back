package edu.eci.cvds.elysium.repository;

import java.util.List;

import edu.eci.cvds.elysium.model.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Usuario findById(int idInstitucional);
    List<Usuario> findAll();
    List<Usuario> findAllActive();
    List<Usuario> findAllInactive();
    List<Usuario> findAllAdmins();
    List<Usuario> findAllActiveAdmins();
    List<Usuario> findAllInactiveAdmins();
    List<Usuario> findAllActiveNoAdmins();
    List<Usuario> findAllInactiveNoAdmins();
    // The logic of the delete is going to be changed to inactive and it is going to be put in AdministradorService
    //void deleteById(int idInstitucional);
}
