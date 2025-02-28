package edu.eci.cvds.elysium.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.cvds.elysium.model.RecursoModel;

public interface RecursoRepository extends MongoRepository<RecursoModel, String> {
    // Se pueden agregar consultas personalizadas si es necesario
}
