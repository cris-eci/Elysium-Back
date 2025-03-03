package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.RecursoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;

public interface RecursoRepository extends MongoRepository<RecursoModel, ObjectId> {

    List<RecursoModel> findAll();
    List<RecursoModel> findByNombre(String nombre);
    List<RecursoModel> findByCantidad(int cantidad);
    List<RecursoModel> findByEspecificaciones(List<String> especificaciones);
    RecursoModel findById(UUID id);


}
