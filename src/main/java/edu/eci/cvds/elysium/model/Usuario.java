package edu.eci.cvds.elysium.model;

import java.util.List;

public abstract class Usuario {
    protected boolean isAdmin;
    protected int idInstitucional;
    protected String nombre;
    protected String apellido;
    protected String correoInstitucional;
    protected boolean activo;

    public Usuario(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo, boolean isAdmin) {
        this.idInstitucional = idInstitucional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoInstitucional = correoInstitucional;
        this.activo = activo;
        this.isAdmin = isAdmin;
    }
    
    // Getters y setters
    public int getIdInstitucional() {
        return idInstitucional;
    }

    public void setIdInstitucional(int idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    // Método abstracto que se implementará en Administrador
    public abstract List<Salon> getSalones();

    public void setAdmin(boolean esAdmin) {
        this.isAdmin = esAdmin;
    }   
}
