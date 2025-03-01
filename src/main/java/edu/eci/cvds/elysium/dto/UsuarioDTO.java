package edu.eci.cvds.elysium.dto;
// DTO para recibir los datos en JSON
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;

    // Constructor
    public UsuarioDTO(int id, String nombre, String apellido, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
}