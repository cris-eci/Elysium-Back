package edu.eci.cvds.elysium.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "recursos")
public class RecursoModel {

    @Id
    private String id;
    private String nombre;
    private int cantidad;
    private List<String> especificaciones;

    // Constructor sin parámetros (requerido por Spring Data)
    public RecursoModel() {}

    // Constructor con parámetros
    public RecursoModel(String id, String nombre, int cantidad, List<String> especificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(List<String> especificaciones) {
        this.especificaciones = especificaciones;
    }

    /**
     * Actualiza el recurso. Se actualizan solo los campos no nulos.
     * @param nuevoNombre nuevo nombre (si es null, se mantiene el actual)
     * @param nuevaCantidad nueva cantidad (si es null, se mantiene el actual)
     * @param nuevasEspecificaciones nuevas especificaciones (si es null, se mantiene el actual)
     */
    public void actualizar(String nuevoNombre, Integer nuevaCantidad, List<String> nuevasEspecificaciones) {
        if (nuevoNombre != null) {
            this.nombre = nuevoNombre;
        }
        if (nuevaCantidad != null) {
            this.cantidad = nuevaCantidad;
        }
        if (nuevasEspecificaciones != null) {
            this.especificaciones = nuevasEspecificaciones;
        }
    }
}
