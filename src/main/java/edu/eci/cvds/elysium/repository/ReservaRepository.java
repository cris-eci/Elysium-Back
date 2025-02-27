package edu.eci.cvds.elysium.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.cvds.elysium.model.ReservaModel;

public interface ReservaRepository extends MongoRepository<ReservaModel, String> {
    List<ReservaModel> findByIdLaboratorioAndFechaReserva(String idLaboratorio, LocalDate fechaReserva);
}
