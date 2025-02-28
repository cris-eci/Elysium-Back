package edu.eci.cvds.elysium.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.service.RecursoService;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

    private final RecursoService recursoService;

    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    /**
     * Endpoint para crear un recurso.
     * Método: POST /recursos
     */
    @PostMapping
    public ResponseEntity<?> crearRecurso(@RequestBody RecursoModel recurso) {
        try {
            RecursoModel creado = recursoService.crearRecurso(recurso);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para actualizar un recurso.
     * Solo se permite si el header "X-Role" indica ADMIN.
     * Método: PUT /recursos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRecurso(@PathVariable String id,
                                               @RequestBody RecursoModel recursoActualizado,
                                               @RequestHeader(value = "X-Role", required = false) String role) {
        boolean isAdmin = role != null && role.equalsIgnoreCase("ADMIN");
        try {
            RecursoModel actualizado = recursoService.actualizarRecurso(id, recursoActualizado, isAdmin);
            return ResponseEntity.ok(actualizado);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint para eliminar un recurso.
     * Solo se permite si el header "X-Role" indica ADMIN.
     * Método: DELETE /recursos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRecurso(@PathVariable String id,
                                               @RequestHeader(value = "X-Role", required = false) String role) {
        boolean isAdmin = role != null && role.equalsIgnoreCase("ADMIN");
        try {
            boolean eliminado = recursoService.eliminarRecurso(id, isAdmin);
            if (eliminado) {
                return ResponseEntity.ok("Recurso eliminado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado");
            }
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
