package com.example.empleados_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpleadosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadosApiApplication.class, args);

		System.out.println("\n🚀 ===== API DE EMPLEADOS INICIADA =====");
		System.out.println("📍 URL Base: http://localhost:8080");
		System.out.println("\n📋 Endpoints disponibles:");
		System.out.println("   GET    /api/empleados                    → Obtener todos los empleados");
		System.out.println("   GET    /api/empleados/{id}               → Obtener empleado por ID");
		System.out.println("   POST   /api/empleados                    → Crear nuevo empleado");
		System.out.println("   GET    /api/empleados/puesto/{puesto}    → Buscar por puesto");
		System.out.println("   DELETE /api/empleados/{id}               → Eliminar empleado");
		System.out.println("   GET    /api/empleados/estadisticas       → Ver estadísticas");
		System.out.println("\n🧪 Ejemplos de prueba:");
		System.out.println("   curl -X GET http://localhost:8080/api/empleados");
		System.out.println("   curl -X GET http://localhost:8080/api/empleados/puesto/Backend");
		System.out.println("   curl -X DELETE http://localhost:8080/api/empleados/2");
		System.out.println("\n✅ ¡API lista para usar!");
	}
}
