package edu.eci.cvds.elysium.dto;

import java.util.List;

/**
 * RecursoDTO is a Data Transfer Object that represents a resource with a name, quantity, and specifications.
 */
public class RecursoDTO {

    private String nombre;
    private int cantidad;
    private List<String> especificaciones;

    public RecursoDTO() {
    }

    public RecursoDTO(String nombre, int cantidad, List<String> especificaciones) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
    }

    // Getters

    public String getNombre(){return nombre;}
    public int getCantidad(){return cantidad;}
    public List<String> getEspecificaciones(){return especificaciones;}
    
}
