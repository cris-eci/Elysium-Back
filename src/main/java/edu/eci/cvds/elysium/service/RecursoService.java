package edu.eci.cvds.elysium.service;

import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.repository.RecursoRepository;

import java.util.UUID;

@Service
public class RecursoService {

    private final RecursoRepository recursoRepository;

    public RecursoService(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    /**
     * Crea un nuevo recurso. Si el ID no se suministra, se genera automáticamente.
     */
    public RecursoModel crearRecurso(RecursoModel recurso) {
        if (recurso.getId() == null || recurso.getId().isEmpty()) {
            recurso.setId(UUID.randomUUID().toString());
        }
        return recursoRepository.save(recurso);
    }

    /**
     * Actualiza un recurso existente.
     * Solo se permite si isAdmin es verdadero.
     * Se actualizan solo los campos que no sean null.
     */
    public RecursoModel actualizarRecurso(String id, RecursoModel recursoActualizado, boolean isAdmin) {
        if (!isAdmin) {
            throw new SecurityException("No tiene permisos para actualizar el recurso");
        }
        RecursoModel recursoExistente = recursoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Recurso no encontrado"));
        // Se actualiza el recurso delegando en el método de la entidad
        recursoExistente.actualizar(recursoActualizado.getNombre(),
                recursoActualizado.getCantidad(),
                recursoActualizado.getEspecificaciones());
        return recursoRepository.save(recursoExistente);
    }

    /**
     * Elimina un recurso. Solo se permite si isAdmin es verdadero.
     */
    public boolean eliminarRecurso(String id, boolean isAdmin) {
        if (!isAdmin) {
            throw new SecurityException("No tiene permisos para eliminar el recurso");
        }
        return recursoRepository.findById(id).map(recurso -> {
            recursoRepository.delete(recurso);
            return true;
        }).orElse(false);
    }
}
