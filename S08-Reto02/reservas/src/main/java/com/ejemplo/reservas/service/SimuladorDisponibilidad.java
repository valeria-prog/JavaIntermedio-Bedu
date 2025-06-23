package com.ejemplo.reservas.service;

import org.springframework.stereotype.Service;

@Service
public class SimuladorDisponibilidad implements DisponibilidadService {
    @Override
    public boolean hayDisponibilidad(String fecha, int personas) {
        // Simulación: disponible si son <= 4 personas
        return personas <= 4;
    }
}
