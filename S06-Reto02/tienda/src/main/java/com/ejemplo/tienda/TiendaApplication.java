package com.ejemplo.tienda;

import com.ejemplo.tienda.entity.Marca;
import com.ejemplo.tienda.entity.Producto;
import com.ejemplo.tienda.repository.MarcaRepository;
import com.ejemplo.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaApplication implements CommandLineRunner {

	@Autowired
	private ProductoRepository productoRepo;

	@Autowired
	private MarcaRepository marcaRepo;

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🛍️ Iniciando sistema de tienda con relaciones JPA...\n");

		// Limpiar datos previos
		productoRepo.deleteAll();
		marcaRepo.deleteAll();

		// 1. Crear al menos 2 marcas
		System.out.println("🏷️ Creando marcas...");

		Marca apple = new Marca("Apple");
		Marca samsung = new Marca("Samsung");

		// Guardar marcas
		apple = marcaRepo.save(apple);
		samsung = marcaRepo.save(samsung);

		System.out.println("✅ Marcas creadas: " + apple.getNombre() + ", " + samsung.getNombre());
		System.out.println();

		// 2. Asociar al menos 2 productos a cada marca
		System.out.println("📱 Creando productos por marca...");

		// Productos de Apple
		Producto iphone = new Producto("iPhone 15", "Smartphone de última generación", 25000.00, apple);
		Producto ipad = new Producto("iPad Pro", "Tablet profesional con M2", 18000.00, apple);

		// Productos de Samsung
		Producto galaxy = new Producto("Galaxy S23", "Smartphone Android premium", 22000.00, samsung);
		Producto smartTV = new Producto("Smart TV", "Televisor 55 pulgadas 4K", 15000.00, samsung);

		// Guardar productos
		productoRepo.save(iphone);
		productoRepo.save(ipad);
		productoRepo.save(galaxy);
		productoRepo.save(smartTV);

		System.out.println("✅ Productos creados y asociados a sus marcas!");
		System.out.println();

		// 3. Mostrar productos agrupados por marca
		System.out.println("📚 Productos por marca:");
		marcaRepo.findAll().forEach(marca -> {
			System.out.println("🏷️ " + marca.getNombre() + ":");
			productoRepo.findAll().stream()
					.filter(p -> p.getMarca().getId().equals(marca.getId()))
					.forEach(p -> System.out.println("   - " + p.getNombre()));
		});
		System.out.println();

		// 4. Mostrar información adicional de las relaciones
		System.out.println("🔍 Información detallada:");
		System.out.println("Total de marcas: " + marcaRepo.count());
		System.out.println("Total de productos: " + productoRepo.count());
		System.out.println();

		// 5. Ejemplo de consultas con relaciones
		System.out.println("🔎 Consultas con relaciones:");

		// Productos de Apple
		System.out.println("Productos de Apple:");
		productoRepo.findByMarca(apple).forEach(p ->
				System.out.println("   - " + p.getNombre() + " ($" + p.getPrecio() + ")"));

		System.out.println();

		// Productos de Samsung
		System.out.println("Productos de Samsung:");
		productoRepo.findByMarca(samsung).forEach(p ->
				System.out.println("   - " + p.getNombre() + " ($" + p.getPrecio() + ")"));

		System.out.println();
		System.out.println("🎉 Demostración de relaciones JPA completada!");
	}
}
