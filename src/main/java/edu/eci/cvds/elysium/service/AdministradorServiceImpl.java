package edu.eci.cvds.elysium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.Administrador;
import edu.eci.cvds.elysium.model.Estandar;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;

@Service
public class AdministradorServiceImpl extends UsuarioServiceImpl implements AdministradorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void actualizarInformacionUsuario(int idInstitucional, char tipoCampo, String value) {
        Usuario usuario = usuarioRepository.findById(idInstitucional);
        if (usuario != null) {
            if (usuario instanceof Administrador) {
                ((Administrador) usuario).actualizar(idInstitucional, tipoCampo, value);
            } else {
                // Para usuarios estandar actualizamos los campos comunes
                switch (tipoCampo) {
                    case 'n':
                        usuario.setNombre(value);
                        break;
                    case 'a':
                        usuario.setApellido(value);
                        break;
                    case 'c':
                        usuario.setCorreoInstitucional(value);
                        break;
                    default:
                        break;
                }
            }
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void deshabilitarUsuario(int idInstitucional) {
        Usuario usuario = usuarioRepository.findById(idInstitucional);
        if (usuario != null) {
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional) {
        // Se crean usuarios de tipo Estandar al agregarlos desde un administrador
        Estandar nuevoUsuario = new Estandar(idInstitucional, nombre, apellido, correoInstitucional, true);
        usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public void añadirSalon(int adminId, String nombre, String ubicacion, int capacidad) {
        Usuario usuario = usuarioRepository.findById(adminId);
        if (usuario != null && usuario instanceof Administrador) {
            Administrador admin = (Administrador) usuario;
            admin.añadirSalon(nombre, ubicacion, capacidad);
            usuarioRepository.save(admin);
        }
    }
    
}
