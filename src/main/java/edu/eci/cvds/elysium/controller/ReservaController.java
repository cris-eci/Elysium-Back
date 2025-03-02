package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    /**
     * Endpoint para crear una reserva.
     * Método: POST /api/reservas
     */
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDto) {
        try {
            ReservaModel reserva = ReservaDTO.toModel(reservaDto);
            ReservaModel creada = reservaService.crearReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(ReservaDTO.fromModel(creada));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para actualizar una reserva.
     * Método: PUT /api/reservas/{idReserva}
     */
    @PutMapping("/{idReserva}")
    public ResponseEntity<?> actualizarReserva(@PathVariable String idReserva, @RequestBody ReservaDTO reservaDto) {
        try {
            ReservaModel reservaActualizada = ReservaDTO.toModel(reservaDto);
            ReservaModel actualizada = reservaService.actualizarReserva(idReserva, reservaActualizada);
            return ResponseEntity.ok(ReservaDTO.fromModel(actualizada));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para cancelar una reserva.
     * Método: PUT /api/reservas/{idReserva}/cancelar?horaActual=HH:mm
     */
    @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<?> cancelarReserva(@PathVariable String idReserva,
                                               @RequestParam("horaActual") String horaActualStr) {
        try {
            LocalTime horaActual = LocalTime.parse(horaActualStr);
            ReservaModel cancelada = reservaService.cancelarReserva(idReserva, horaActual);
            return ResponseEntity.ok(ReservaDTO.fromModel(cancelada));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para eliminar (marcar como ELIMINADA) una reserva.
     * Método: DELETE /api/reservas/{idReserva}
     * Requiere header: X-Role=ADMIN
     */
    @DeleteMapping("/{idReserva}")
    public ResponseEntity<?> eliminarReserva(@PathVariable String idReserva,
                                               @RequestHeader(value = "X-Role", required = false) String role) {
        try {
            boolean eliminado = reservaService.eliminarReserva(idReserva);
            if (eliminado) {
                return ResponseEntity.ok("Reserva eliminada con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
            }
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
