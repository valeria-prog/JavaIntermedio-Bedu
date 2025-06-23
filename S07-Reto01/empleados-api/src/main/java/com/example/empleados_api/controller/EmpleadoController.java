package com.example.empleados_api.controller;

import com.example.empleados_api.model.Empleado;
import com.example.empleados_api.service.EmpleadoService;
import com.example.empleados_api.exception.EmpleadoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*") // Para permitir CORS en desarrollo
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Endpoint existente - GET todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosLosEmpleados();
        return ResponseEntity.ok(empleados);
    }

    // Endpoint existente - GET empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        try {
            Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
            return ResponseEntity.ok(empleado);
        } catch (EmpleadoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint existente - POST crear nuevo empleado
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // NUEVO ENDPOINT - GET buscar empleados por puesto
    @GetMapping("/puesto/{puesto}")
    public ResponseEntity<?> buscarEmpleadosPorPuesto(@PathVariable String puesto) {
        try {
            List<Empleado> empleados = empleadoService.buscarPorPuesto(puesto);

            if (empleados.isEmpty()) {
                Map<String, String> respuesta = new HashMap<>();
                respuesta.put("mensaje", "No se encontraron empleados con el puesto: " + puesto);
                respuesta.put("puesto_buscado", puesto);
                return ResponseEntity.ok(respuesta);
            }

            return ResponseEntity.ok(empleados);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // NUEVO ENDPOINT - DELETE eliminar empleado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        try {
            empleadoService.eliminarEmpleado(id);

            // Respuesta de éxito con mensaje informativo
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Empleado con ID " + id + " eliminado correctamente");
            respuesta.put("id_eliminado", id);
            respuesta.put("status", "success");

            return ResponseEntity.ok(respuesta);

        } catch (EmpleadoNoEncontradoException e) {
            // Respuesta de error cuando no se encuentra el empleado
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("id_buscado", id);
            error.put("status", "not_found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint adicional - GET estadísticas (bonus)
    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        List<Empleado> todosLosEmpleados = empleadoService.obtenerTodosLosEmpleados();

        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("total_empleados", todosLosEmpleados.size());

        // Contar empleados por puesto
        Map<String, Long> empleadosPorPuesto = todosLosEmpleados.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        Empleado::getPuesto,
                        java.util.stream.Collectors.counting()
                ));

        estadisticas.put("empleados_por_puesto", empleadosPorPuesto);

        // Salario promedio
        double salarioPromedio = todosLosEmpleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);

        estadisticas.put("salario_promedio", Math.round(salarioPromedio * 100.0) / 100.0);

        return ResponseEntity.ok(estadisticas);
    }
}
