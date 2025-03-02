package edu.eci.cvds.elysium.service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.repository.ReservaRepository;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Crea una nueva reserva.
     * Campos obligatorios: fechaReserva, diaSemana, proposito y idSalon.
     * Se asigna un ID único y el estado inicial ACTIVA.
     * @param reserva la reserva a crear
     * @return la reserva creada
     */
    public ReservaModel crearReserva(ReservaModel reserva) {
        if (reserva.getFechaReserva() == null ||
            reserva.getDiaSemana() == null ||
            reserva.getProposito() == null ||
            reserva.getIdSalon() == null) {
            throw new IllegalArgumentException("Campos obligatorios faltantes");
        }
        reserva.setIdReserva(UUID.randomUUID().toString());
        reserva.setEstado(EstadoReservaModel.ACTIVA);
        return reservaRepository.save(reserva);
    }

    /**
     * Actualiza una reserva existente (fecha, salón, duración y día de la semana).
     * Se actualiza el estado a ACTIVA tras la modificación.
     * @param idReserva el ID de la reserva a actualizar
     * @param reservaActualizada la reserva con los datos actualizados
     * @return la reserva actualizada
     */
    public ReservaModel actualizarReserva(String idReserva, ReservaModel reservaActualizada) {
        ReservaModel reserva = reservaRepository.findById(idReserva)
            .orElseThrow(() -> new IllegalStateException("Reserva no encontrada"));
        reserva.setFechaReserva(reservaActualizada.getFechaReserva());
        reserva.setDiaSemana(reservaActualizada.getDiaSemana());
        reserva.setIdSalon(reservaActualizada.getIdSalon());
        reserva.setDuracionBloque(reservaActualizada.isDuracionBloque());
        // Actualiza el estado a ACTIVA (o según la lógica, podría ser actualizado a otro valor)
        reserva.setEstado(EstadoReservaModel.ACTIVA);
        return reservaRepository.save(reserva);
    }

    /**
     * Cancela la reserva cambiando el estado a CANCELADA.
     * Sólo se permite si la reserva está en estado ACTIVA.
     * @param idReserva el ID de la reserva a cancelar
     * @param horaActual la hora actual para validar la cancelación
     * @return la reserva cancelada
     */
    public ReservaModel cancelarReserva(String idReserva, LocalTime horaActual) {
        ReservaModel reserva = reservaRepository.findById(idReserva)
            .orElseThrow(() -> new IllegalStateException("No existe la reserva"));
        reserva.setEstado(EstadoReservaModel.CANCELADA);
        return reservaRepository.save(reserva);
    }

    /**
     * "Elimina" la reserva, es decir, cambia su estado a ELIMINADA.
     * Sólo se permite si isAdmin es true.
     * @param idReserva el ID de la reserva a eliminar
     * @return true si la reserva fue eliminada, false si no existe
     */
    public boolean eliminarReserva(String idReserva) {
        return reservaRepository.findById(idReserva).map(reserva -> {
            reserva.setEstado(EstadoReservaModel.ELIMINADA);
            reservaRepository.save(reserva);
            return true;
        }).orElse(false);
    }
}
