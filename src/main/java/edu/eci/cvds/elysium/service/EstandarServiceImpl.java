package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.Estandar;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.EstandarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class EstandarServiceImpl extends UsuarioServiceImpl implements EstandarService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Reserva crearReserva(int idInstitucional, LocalTime fechaInicio, String proposito, String mnemonico) {
        Usuario usuario = usuarioRepository.findById(idInstitucional);
        if (usuario != null && usuario instanceof Estandar) {
            Estandar estandar = (Estandar) usuario;
            return estandar.crearReserva(fechaInicio, proposito, mnemonico);
        }
        return null;
    }
}
