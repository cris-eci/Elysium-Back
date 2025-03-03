package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.model.SalonModel;

public interface ReservaService {

    List<ReservaModel> consultarReservas();
    List<ReservaModel> consultarReservasPorSalon(SalonModel salon);
    List<ReservaModel> consultarReservasPorFecha(LocalDate fecha);
    List<ReservaModel> consultarReservasPorDiaSemana(DiaSemanaModel diaSemana);
    List<ReservaModel> consultarReservasPorEstado(EstadoReservaModel estado);
    List<ReservaModel> consultarReservasPorDuracionBloque(boolean duracionBloque);
    ReservaModel consultarReserva(String idReserva);
    void crearReserva(String idReserva,LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, SalonModel salon,boolean duracionBloque);
    void actualizarReserva(String idReserva,char tipoCampo,LocalDate value1,DiaSemanaModel value2,SalonModel value3,boolean value4 );
    void deleteReserva(String idReserva);
    void cancelReserva(String idReserva);
    void rechazarReserva(String idReserva);

} 

