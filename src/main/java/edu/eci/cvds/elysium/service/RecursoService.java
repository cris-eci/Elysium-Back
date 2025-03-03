package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.RecursoModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public interface RecursoService {
    List<RecursoModel> consultarRecursos();
    List<RecursoModel> consultarNombre(String nombre);
    List<RecursoModel> consultarCantidad(int cantidad);
    List<RecursoModel> consultarEspecificaciones(List<String> especificaciones);
    RecursoModel consultarRecurso(UUID id);
    void agregarRecurso(String nombre, int cantidad, List<String> especificaciones);
    void actualizarRecurso(String nombre, int cantidad, List<String> especificaciones);
    void eliminarRecurso(UUID id);
} 


