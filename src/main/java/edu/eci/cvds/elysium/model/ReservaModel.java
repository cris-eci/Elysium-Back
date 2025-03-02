package edu.eci.cvds.elysium.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a reservation model for the "reservas" collection.
 */
@Document(collection = "reservas")
public class ReservaModel {

    @Id
    private String idReserva;
    private LocalDate fechaReserva;
    private DiaSemanaModel diaSemana;
    private String proposito;
    private String salon;
    private EstadoReservaModel estado;
    private boolean duracionBloque;

    /**
     * Default constructor for a ReservaModel instance.
     */
    public ReservaModel() {
    }

    /**
     * Constructor to create a new ReservaModel instance.
     *
     * @param fechaReserva   the date of the reservation
     * @param diaSemana      the day of the week of the reservation
     * @param proposito      the purpose of the reservation
     * @param salon          the salon associated with the reservation
     * @param duracionBloque the duration block of the reservation
     */
    public ReservaModel(LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, String salon, boolean duracionBloque) {
        this.fechaReserva = fechaReserva;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.salon = salon;
        this.duracionBloque = duracionBloque;
    }

    /**
     * Gets the reservation ID.
     *
     * @return the reservation ID
     */
    public String getIdReserva() {
        return idReserva;
    }

    /**
     * Sets the reservation ID.
     *
     * @param idReserva the reservation ID
     */
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Gets the date of the reservation.
     *
     * @return the date of the reservation
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Sets the date of the reservation.
     *
     * @param fechaReserva the date of the reservation
     */
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Gets the day of the week of the reservation.
     *
     * @return the day of the week of the reservation
     */
    public DiaSemanaModel getDiaSemana() {
        return diaSemana;
    }

    /**
     * Sets the day of the week of the reservation.
     *
     * @param diaSemana the day of the week of the reservation
     */
    public void setDiaSemana(DiaSemanaModel diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * Gets the purpose of the reservation.
     *
     * @return the purpose of the reservation
     */
    public String getProposito() {
        return proposito;
    }

    /**
     * Sets the purpose of the reservation.
     *
     * @param proposito the purpose of the reservation
     */
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    /**
     * Gets the salon ID associated with the reservation.
     *
     * @return the salon ID
     */
    public String getIdSalon() {
        return salon;
    }

    /**
     * Sets the salon ID associated with the reservation.
     *
     * @param idSalon the salon ID
     */
    public void setIdSalon(String idSalon) {
        this.salon = idSalon;
    }

    /**
     * Gets the state of the reservation.
     *
     * @return the state of the reservation
     */
    public EstadoReservaModel getEstado() {
        return estado;
    }

    /**
     * Sets the state of the reservation.
     *
     * @param estado the state of the reservation
     */
    public void setEstado(EstadoReservaModel estado) {
        this.estado = estado;
    }

    /**
     * Checks if the reservation duration is a block.
     *
     * @return true if the reservation duration is a block, false otherwise
     */
    public boolean isDuracionBloque() {
        return duracionBloque;
    }

    /**
     * Sets the reservation duration block.
     *
     * @param duracionBloque the reservation duration block
     */
    public void setDuracionBloque(boolean duracionBloque) {
        this.duracionBloque = duracionBloque;
    }
}
