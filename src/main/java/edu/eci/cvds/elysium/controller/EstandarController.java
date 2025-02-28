package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.service.EstandarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/estandar")
public class EstandarController {

    @Autowired
    private EstandarService estandarService;

    @PostMapping("/crearReserva")
    public Reserva crearReserva(@RequestParam int id,
                                @RequestParam String fechaInicio,  // formato "HH:mm"
                                @RequestParam String proposito,
                                @RequestParam String mnemonico) {
        LocalTime time = LocalTime.parse(fechaInicio);
        return estandarService.crearReserva(id, time, proposito, mnemonico);
    }
}
