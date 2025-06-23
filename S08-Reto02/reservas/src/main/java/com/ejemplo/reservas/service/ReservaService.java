package com.ejemplo.reservas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);
    private final DisponibilidadService disponibilidadService;

    public ReservaService(DisponibilidadService disponibilidadService) {
        this.disponibilidadService = disponibilidadService;
    }

    public boolean realizarReserva(String fecha, int personas) {
        boolean disponible = disponibilidadService.hayDisponibilidad(fecha, personas);
        if (disponible) {
            logger.info("✅ Reserva confirmada para {} personas el {}", personas, fecha);
        } else {
            logger.info("❌ Reserva rechazada para {} personas el {} (sin disponibilidad)", personas, fecha);
        }
        return disponible;
    }
}

