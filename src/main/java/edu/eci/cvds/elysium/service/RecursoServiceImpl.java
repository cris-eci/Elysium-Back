package edu.eci.cvds.elysium.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.repository.RecursoRepository;

@Service
public class RecursoServiceImpl implements RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    @Override
    public List<RecursoModel> consultarRecursos() {
        return recursoRepository.findAll();
    }

    @Override
    public List<RecursoModel> consultarNombre(String nombre) {
        return recursoRepository.findByNombre(nombre);
    }

    @Override
    public List<RecursoModel> consultarCantidad(int cantidad) {
        return recursoRepository.findByCantidad(cantidad);
    }

    @Override
    public List<RecursoModel> consultarEspecificaciones(List<String> especificaciones) {
        return recursoRepository.findByEspecificaciones(especificaciones);
    }

    @Override
    public RecursoModel consultarRecurso(UUID id) {
        return recursoRepository.findById(id);
    }

    @Override
    public void agregarRecurso(String nombre, int cantidad, List<String> especificaciones) {
        RecursoModel recurso = new RecursoModel(nombre, cantidad, especificaciones);
        recurso.crearRecurso(nombre, cantidad, especificaciones);
        recursoRepository.save(recurso);
    }

    @Override
    public void actualizarRecurso(String nombre, int cantidad, List<String> especificaciones) {
        RecursoModel recurso = recursoRepository.findById(UUID.randomUUID());
        if(recurso != null ){
            recurso.actualizar(nombre, cantidad, especificaciones);
            recursoRepository.save(recurso);
        }
    }

    @Override
    public void eliminarRecurso(UUID id) {
        RecursoModel recurso = recursoRepository.findById(id);
        if(recurso != null){
            recursoRepository.delete(recurso);
            recursoRepository.save(recurso);
        }
    }
    
}
