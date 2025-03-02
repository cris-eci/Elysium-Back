package edu.eci.cvds.elysium.service;

import java.util.List;

import edu.eci.cvds.elysium.model.Usuario;

public interface AdministradorService extends UsuarioService {
    List<Usuario> consultarUsuarios();
    List<Usuario> consultarUsuariosActivos();
    List<Usuario> consultarUsuariosInactivos();
    List<Usuario> consultarUsuariosAdmins();
    List<Usuario> consultarUsuariosActiveAdmins();
    List<Usuario> consultarUsuariosInactiveAdmins();
    List<Usuario> consultarUsuariosActiveNoAdmins();
    List<Usuario> consultarUsuariosInactiveNoAdmins();
    void agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean isAdmin);
    void actualizarInformacionUsuario(int idInstitucional, char tipoCampo, String value);
    void habilitarUsuario(int idInstitucional);
    void deshabilitarUsuario(int idInstitucional);
    void hacerAdmin(int id);
    void añadirSalon(int adminId, String nombre, String ubicacion, int capacidad);
}
