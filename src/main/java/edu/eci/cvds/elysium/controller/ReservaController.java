package edu.eci.cvds.elysium.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.service.ReservaService;

import java.time.LocalTime;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaModel reservaRequest) {
        try {
            ReservaModel creada = reservaService.crearReserva(reservaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idReserva}")
    public ResponseEntity<?> eliminarReserva(@PathVariable String idReserva,
                                               @RequestHeader(value = "X-Role", required = false) String role) {
        boolean isAdmin = role != null && role.equalsIgnoreCase("ADMIN");
        try {
            boolean eliminado = reservaService.eliminarReserva(idReserva, isAdmin);
            if (eliminado) {
                return ResponseEntity.ok("Reserva eliminada con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
            }
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<?> cancelarReserva(@PathVariable String idReserva,
                                               @RequestParam("horaActual") String horaActualStr) {
        try {
            LocalTime horaActual = LocalTime.parse(horaActualStr);
            ReservaModel r = reservaService.cancelarReserva(idReserva, horaActual);
            return ResponseEntity.ok(r);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Se pueden agregar endpoints adicionales, por ejemplo para actualizar reservas.
}