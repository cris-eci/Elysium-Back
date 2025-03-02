package edu.eci.cvds.elysium.dto;

import java.time.LocalDate;
import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;

public class ReservaDTO {
    
    private String idReserva;
    private LocalDate fechaReserva;
    // Se representa el día de la semana como String (por ejemplo, "LUNES")
    private String diaSemana;
    private String proposito;
    // Se utiliza un String para identificar el salón
    private String idSalon;
    // Estado de la reserva, p.ej., "ACTIVA"
    private String estado;
    // true: reserva de bloque; false: reserva corta
    private boolean duracionBloque;

    public ReservaDTO() {
    }

    public ReservaDTO(String idReserva, LocalDate fechaReserva, String diaSemana, String proposito, String idSalon, String estado, boolean duracionBloque) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.diaSemana = diaSemana;
        this.proposito = proposito;
        this.idSalon = idSalon;
        this.estado = estado;
        this.duracionBloque = duracionBloque;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(String idSalon) {
        this.idSalon = idSalon;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isDuracionBloque() {
        return duracionBloque;
    }

    public void setDuracionBloque(boolean duracionBloque) {
        this.duracionBloque = duracionBloque;
    }

    // Métodos de conversión:

    public static ReservaDTO fromModel(ReservaModel reserva) {
        return new ReservaDTO(
            reserva.getIdReserva(),
            reserva.getFechaReserva(),
            reserva.getDiaSemana() != null ? reserva.getDiaSemana().name() : null,
            reserva.getProposito(),
            reserva.getIdSalon(),
            reserva.getEstado() != null ? reserva.getEstado().name() : null,
            reserva.isDuracionBloque()
        );
    }

    public static ReservaModel toModel(ReservaDTO dto) {
        ReservaModel reserva = new ReservaModel();
        reserva.setIdReserva(dto.getIdReserva());
        reserva.setFechaReserva(dto.getFechaReserva());
        if(dto.getDiaSemana() != null) {
            reserva.setDiaSemana(DiaSemanaModel.valueOf(dto.getDiaSemana()));
        }
        reserva.setProposito(dto.getProposito());
        reserva.setIdSalon(dto.getIdSalon());
        if(dto.getEstado() != null) {
            reserva.setEstado(EstadoReservaModel.valueOf(dto.getEstado()));
        }
        reserva.setDuracionBloque(dto.isDuracionBloque());
        return reserva;
    }
}
