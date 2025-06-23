package com.ejemplo.reservas.controller;

import com.ejemplo.reservas.service.ReservaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public String reservar(@RequestParam String fecha, @RequestParam int personas) {
        boolean confirmada = reservaService.realizarReserva(fecha, personas);
        return confirmada ? "Reserva confirmada ✅" : "Reserva rechazada ❌";
    }
}

