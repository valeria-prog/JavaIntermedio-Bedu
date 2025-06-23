package com.ejemplo.inventario;
import com.ejemplo.inventario.entity.Producto;
import com.ejemplo.inventario.repository.ProductoRepository;

import com.ejemplo.inventario.entity.Producto;
import com.ejemplo.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class InventarioApplication implements CommandLineRunner {

	@Autowired
	private ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🚀 Iniciando sistema de inventario...\n");

		// Limpiar datos previos
		productoRepository.deleteAll();

		// 1. Guardar al menos 4 productos
		System.out.println("💾 Guardando productos...");

		Producto laptop = new Producto("Laptop Lenovo", "Laptop para trabajo y gaming", 12500.00);
		Producto mouse = new Producto("Mouse Logitech", "Mouse inalámbrico ergonómico", 350.00);
		Producto teclado = new Producto("Teclado Mecánico", "Teclado mecánico RGB", 950.00);
		Producto monitor = new Producto("Monitor", "Monitor 24 pulgadas Full HD", 3200.00);

		productoRepository.save(laptop);
		productoRepository.save(mouse);
		productoRepository.save(teclado);
		productoRepository.save(monitor);

		System.out.println("✅ Productos guardados exitosamente!\n");

		// 2. Productos con precio mayor a 500
		System.out.println("📦 Productos con precio mayor a 500:");
		List<Producto> productosCaros = productoRepository.findByPrecioGreaterThan(500);
		for (Producto p : productosCaros) {
			System.out.println(p);
		}
		System.out.println();

		// 3. Productos que contengan "lap" en su nombre
		System.out.println("🔍 Productos que contienen 'lap':");
		List<Producto> productosLap = productoRepository.findByNombreContainingIgnoreCase("lap");
		for (Producto p : productosLap) {
			System.out.println(p);
		}
		System.out.println();

		// 4. Productos con precio entre 400 y 1000
		System.out.println("🎯 Productos con precio entre 400 y 1000:");
		List<Producto> productosRango = productoRepository.findByPrecioBetween(400, 1000);
		for (Producto p : productosRango) {
			System.out.println(p);
		}
		System.out.println();

		// 5. Productos cuyo nombre comience con "m" o "M"
		System.out.println("📘 Productos cuyo nombre empieza con 'm':");
		List<Producto> productosM = productoRepository.findByNombreStartingWithIgnoreCase("m");
		for (Producto p : productosM) {
			System.out.println(p);
		}
		System.out.println();

		System.out.println("🎉 Pruebas completadas exitosamente!");
	}
}
