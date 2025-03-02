package edu.eci.cvds.elysium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario createUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario consultarUsuario(int idInstitucional) {
        return usuarioRepository.findById(idInstitucional);
    }

    @Override
    public List<Usuario> consultarUsuariosActivos() {
        return usuarioRepository.findAllActive();
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findAllInactive() {
        return usuarioRepository.findAllInactive();
    }

    @Override
    public List<Usuario> findAllAdmins() {
        return usuarioRepository.findAllAdmins();
    }

    @Override
    public List<Usuario> findAllActiveAdmins() {
        return usuarioRepository.findAllActiveAdmins();
    }

    @Override
    public List<Usuario> findAllInactiveAdmins() {
        return usuarioRepository.findAllInactiveAdmins();
    }

    @Override
    public List<Usuario> findAllActiveNoAdmins() {
        return usuarioRepository.findAllActiveNoAdmins();
    }

    @Override
    public List<Usuario> findAllInactiveNoAdmins() {
        return usuarioRepository.findAllInactiveNoAdmins();
    }

}
