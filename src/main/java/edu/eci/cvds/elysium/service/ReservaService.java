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

    public ReservaModel crearReserva(ReservaModel reserva) {
        // Validar campos obligatorios
        if (reserva.getFechaReserva() == null ||
            reserva.getHoraInicio() == null ||
            reserva.getHoraFin() == null ||
            reserva.getDiaSemana() == null ||
            reserva.getProposito() == null ||
            reserva.getIdLaboratorio() == null ||
            reserva.getIdUsuario() == null) {
            throw new IllegalArgumentException("Campos obligatorios faltantes");
        }

        // Validar solapamiento
        List<ReservaModel> reservasExistentes = reservaRepository.findByIdLaboratorioAndFechaReserva(
                reserva.getIdLaboratorio(), reserva.getFechaReserva()
        );
        for (ReservaModel r : reservasExistentes) {
            if (horariosSeSolapan(r.getHoraInicio(), r.getHoraFin(), reserva.getHoraInicio(), reserva.getHoraFin())) {
                throw new IllegalStateException("Ya existe una reserva en este horario");
            }
        }

        // Asignar un ID único y el estado inicial
        reserva.setIdReserva(UUID.randomUUID().toString());
        reserva.setEstado(EstadoReservaModel.RESERVADA);

        return reservaRepository.save(reserva);
    }

    private boolean horariosSeSolapan(LocalTime inicio1, LocalTime fin1,
                                      LocalTime inicio2, LocalTime fin2) {
        // Existe solapamiento si inicio2 es antes que fin1 y fin2 es después de inicio1.
        return (inicio2.isBefore(fin1) && fin2.isAfter(inicio1));
    }

    public ReservaModel cancelarReserva(String idReserva, LocalTime horaActual) {
        ReservaModel reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IllegalStateException("No existe la reserva"));

        // Verificar que la reserva aún no haya iniciado
        if (horaActual.isAfter(reserva.getHoraInicio()) || horaActual.equals(reserva.getHoraInicio())) {
            throw new IllegalStateException("No se puede cancelar porque la reserva ya inició");
        }

        reserva.setEstado(EstadoReservaModel.CANCELADA);
        return reservaRepository.save(reserva);
    }

    public boolean eliminarReserva(String idReserva, boolean isAdmin) {
        if (!isAdmin) {
            throw new SecurityException("No tiene permisos para eliminar la reserva");
        }

        return reservaRepository.findById(idReserva).map(reserva -> {
            reservaRepository.delete(reserva);
            return true;
        }).orElse(false);
    }

    // Se pueden agregar métodos adicionales para actualizar reservas y otras validaciones.
}
