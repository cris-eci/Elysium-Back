package edu.eci.cvds.elysium.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import edu.eci.cvds.elysium.model.Usuario;

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
    public List<Usuario> findAllActive() {
        List<Usuario> activeUsers = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario.isActivo()) {
                activeUsers.add(usuario);
            }
        }
        return activeUsers;
    }

    @Override
    public List<Usuario> findAllInactive() {
        List<Usuario> inactiveUsers = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (!usuario.isActivo()) {
                inactiveUsers.add(usuario);
            }
        }
        return inactiveUsers;
    }

    @Override
    public List<Usuario> findAllAdmins() {
        List<Usuario> adminUsers = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario.isAdmin()) {
                adminUsers.add(usuario);
            }
        }
        return adminUsers;
    }

    @Override
    public List<Usuario> findAllActiveAdmins() {
        List<Usuario> activeAdminUsers = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario.isAdmin() && usuario.isActivo()) {
                activeAdminUsers.add(usuario);
            }
        }
        return activeAdminUsers;
    }

    @Override
    public List<Usuario> findAllInactiveAdmins() {
        List<Usuario> inactiveAdminUsers = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (usuario.isAdmin() && !usuario.isActivo()) {
                inactiveAdminUsers.add(usuario);
            }
        }
        return inactiveAdminUsers;
    }

    @Override
    public List<Usuario> findAllActiveNoAdmins() {
        List<Usuario> activeNoAdminUsers = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (!usuario.isAdmin() && usuario.isActivo()) {
                activeNoAdminUsers.add(usuario);
            }
        }
        return activeNoAdminUsers;
    }

    @Override
    public List<Usuario> findAllInactiveNoAdmins() {
        List<Usuario> inactiveNoAdminUsers = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            if (!usuario.isAdmin() && !usuario.isActivo()) {
                inactiveNoAdminUsers.add(usuario);
            }
        }
        return inactiveNoAdminUsers;
    }

    // It's not goint to be delete it. Now is just going to be set as inactive
    // @Override
    // public void deleteById(int idInstitucional) {
    //     usuarios.remove(idInstitucional);
    // }
}
