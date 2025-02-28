package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUsuarioRepository implements UsuarioRepository {

    private ConcurrentHashMap<Integer, Usuario> usuarios = new ConcurrentHashMap<>();

    @Override
    public Usuario save(Usuario usuario) {
        usuarios.put(usuario.getIdInstitucional(), usuario);
        return usuario;
    }

    @Override
    public Usuario findById(int idInstitucional) {
        return usuarios.get(idInstitucional);
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public void deleteById(int idInstitucional) {
        usuarios.remove(idInstitucional);
    }
}
