package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.Usuario;
import java.util.List;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Usuario findById(int idInstitucional);
    List<Usuario> findAll();
    void deleteById(int idInstitucional);
}
