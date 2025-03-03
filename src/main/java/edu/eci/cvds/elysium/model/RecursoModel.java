package edu.eci.cvds.elysium.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/**
 * Represents a resource model for the "resources" collection.
 */
@Document(collection = "recursos")
public class RecursoModel {

    @Id
    private ObjectId id; // Se usará ObjectId y Mongo lo generará automáticamente;
    private String nombre;
    private int cantidad;
    private List<String> especificaciones;
    
    public RecursoModel() {
    }

    /**
     * Constructor to create a new ResourceModelinstance.
     * @param nombre name of the resource
     * @param cantidad amount of the resource
     * @param especificaciones specifications of the resource
     */
    public RecursoModel(String nombre, int cantidad, List<String> especificaciones) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
    }

    /**
     * Gets the resource ID.
     *
     * @return the resource ID
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Sets the resource ID.
     * @param id the resource ID
     */
    public void setId(ObjectId id) {
        this.id = id;
    }


    /**
     * Gets the resource name.
     * @return the resource name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the resource name.
     * @param nombre the resource name
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the resource amount.
     * @return the resource amount
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the resource amount.
     * @param cantidad the resource amount
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the resource specifications.
     * @return the resource specifications
     */
    public List<String> getEspecificaciones() {
        return especificaciones;
    }

    /**
     * Sets the resource specifications.
     * @param especificaciones the resource specifications
     */
    public void setEspecificaciones(List<String> especificaciones) {
        this.especificaciones = especificaciones;
    }
    
    /**
     * Creates a new resource with the given data.
     * @param nombre name of the resource
     * @param cantidad amount of the resource
     * @param especificaciones specifications of the resource
     * @return the new resource
     */
    public RecursoModel crearRecurso(String nombre, int cantidad, List<String> especificaciones) {
        return new RecursoModel(nombre, cantidad, especificaciones);
    }


    /**
     * Updates the resource with the new data.
     * @param nuevoNombre new name of the resource
     * @param nuevaCantidad new amount of the resource
     * @param nuevasEspecificaciones new specifications of the resource
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

    /**
     * Deletes the resource.
     */
    public void eliminar(String id) {
        this.nombre = null;
        this.cantidad = 0;
        this.especificaciones = null;
    }

}
