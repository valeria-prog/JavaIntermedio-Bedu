package com.ejemplo.reservas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceTest {

	private DisponibilidadService disponibilidadService;
	private ReservaService reservaService;

	@BeforeEach
	void setUp() {
		disponibilidadService = mock(DisponibilidadService.class); // mockeamos la dependencia
		reservaService = new ReservaService(disponibilidadService); // inyectamos el mock
	}

	@Test
	void debeConfirmarReservaCuandoHayDisponibilidad() {
		// Arrange
		String fecha = "2025-07-01";
		int personas = 3;
		when(disponibilidadService.hayDisponibilidad(fecha, personas)).thenReturn(true);

		// Act
		boolean resultado = reservaService.realizarReserva(fecha, personas);

		// Assert
		assertTrue(resultado);
		verify(disponibilidadService, times(1)).hayDisponibilidad(fecha, personas);
	}

	@Test
	void debeRechazarReservaCuandoNoHayDisponibilidad() {
		// Arrange
		String fecha = "2025-07-02";
		int personas = 6;
		when(disponibilidadService.hayDisponibilidad(fecha, personas)).thenReturn(false);

		// Act
		boolean resultado = reservaService.realizarReserva(fecha, personas);

		// Assert
		assertFalse(resultado);
		verify(disponibilidadService, times(1)).hayDisponibilidad(fecha, personas);
	}
}

