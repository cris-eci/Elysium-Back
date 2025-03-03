package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.service.SalonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import edu.eci.cvds.elysium.dto.salon.ActualizarSalonDTO;

@RestController
@RequestMapping("/api/salones")
public class SalonController {

    @Autowired
    private SalonService salonService;

    // Consulta todos los salones
    @GetMapping("")
    public ResponseEntity<List<Salon>> getAllSalones() {
        List<Salon> salones = salonService.findAll();
        return ResponseEntity.ok(salones);
    }

    // Consulta un salón específico por su mnemonico
    @GetMapping("/{mnemonico}")
    public ResponseEntity<Salon> getSalonByMnemonico(@PathVariable String mnemonico) {
        Salon salon = salonService.findByMnemonico(mnemonico);
        return salon != null ? ResponseEntity.ok(salon) : ResponseEntity.notFound().build();
    }

    // Agregar un nuevo salón
    @PostMapping("")
    public ResponseEntity<Void> agregarSalon(@RequestBody Salon salonRequest) {
        // Se espera que el JSON incluya: nombre, mnemonico, ubicacion, capacidad.
        // Se asume que el salón se crea por defecto como activo y disponible.
        salonService.agregarSalon(
                salonRequest.getNombre(),
                salonRequest.getMnemonico(),
                salonRequest.getUbicacion(),
                salonRequest.getCapacidad()
        );
        
        return ResponseEntity.ok().build();
    }

    // Deshabilitar un salón (marcarlo como inactivo)
    @PutMapping("/{mnemonico}/deshabilitar")
    public ResponseEntity<Void> deshabilitarSalon(@PathVariable String mnemonico) {
        salonService.deshabilitarSalon(mnemonico);
        return ResponseEntity.noContent().build();
    }

    // Habilitar un salón (marcarlo como activo)
    @PutMapping("/{mnemonico}/habilitar")
    public ResponseEntity<Void> habilitarSalon(@PathVariable String mnemonico) {
        salonService.habilitarSalon(mnemonico);
        return ResponseEntity.noContent().build();
    }

    // Actualizar el nombre del salón
    @PutMapping("/{mnemonico}/actualizarNombre")
    public ResponseEntity<Void> actualizarNombre(@RequestBody ActualizarSalonDTO actualizarSalonDTO) {
        salonService.actualizarNombre(actualizarSalonDTO.getMnemonico(), actualizarSalonDTO.getValor());
        return ResponseEntity.noContent().build();
    }

    // Actualizar la ubicación del salón
    @PutMapping("/{mnemonico}/actualizarUbicacion")
    public ResponseEntity<Void> actualizarUbicacion(@RequestBody ActualizarSalonDTO actualizarSalonDTO) {
        salonService.actualizarUbicacion(actualizarSalonDTO.getMnemonico(), actualizarSalonDTO.getValor());
        return ResponseEntity.noContent().build();
    }

    // Actualizar la capacidad del salón
    @PutMapping("/{mnemonico}/actualizarCapacidad")
    public ResponseEntity<Void> actualizarCapacidad(@RequestBody ActualizarSalonDTO actualizarSalonDTO) {
        salonService.actualizarCapacidad(actualizarSalonDTO.getMnemonico(), actualizarSalonDTO.getCapacidad());
        return ResponseEntity.noContent().build();
    }

    // Consultar el estado 'activo' del salón
    @GetMapping("/{mnemonico}/activo")
    public ResponseEntity<Boolean> getActivo(@PathVariable String mnemonico) {
        boolean activo = salonService.getActivo(mnemonico);
        return ResponseEntity.ok(activo);
    }

    // Consultar el estado 'disponible' del salón
    @GetMapping("/{mnemonico}/disponible")
    public ResponseEntity<Boolean> getDisponible(@PathVariable String mnemonico) {
        boolean disponible = salonService.getDisponible(mnemonico);
        return ResponseEntity.ok(disponible);
    }

    // Marcar el salón como disponible
    @PutMapping("/{mnemonico}/disponible")
    public ResponseEntity<Void> setDisponible(@PathVariable String mnemonico) {
        boolean actualizado = salonService.setDisponible(mnemonico);
        return actualizado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Marcar el salón como no disponible
    @PutMapping("/{mnemonico}/noDisponible")
    public ResponseEntity<Void> setNoDisponible(@PathVariable String mnemonico) {
        boolean actualizado = salonService.setNoDisponible(mnemonico);
        return actualizado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
