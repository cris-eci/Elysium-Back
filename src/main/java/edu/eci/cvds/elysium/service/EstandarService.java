package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.Reserva;
import java.time.LocalTime;

public interface EstandarService extends UsuarioService {
    Reserva crearReserva(int idInstitucional, LocalTime fechaInicio, String proposito, String mnemonico);
}
