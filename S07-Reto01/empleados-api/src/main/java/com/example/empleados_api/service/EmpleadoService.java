package com.example.empleados_api.service;

import com.example.empleados_api.model.Empleado;
import com.example.empleados_api.exception.EmpleadoNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private List<Empleado> empleados = new ArrayList<>();
    private Long siguienteId = 1L;

    public EmpleadoService() {
        // Datos iniciales para pruebas
        empleados.add(new Empleado(siguienteId++, "Ana Gómez", "Gerente de Marketing", 55000.0));
        empleados.add(new Empleado(siguienteId++, "Carlos Pérez", "Desarrollador Backend", 45000.0));
        empleados.add(new Empleado(siguienteId++, "María López", "Desarrolladora Frontend", 42000.0));
        empleados.add(new Empleado(siguienteId++, "Juan Rodríguez", "Desarrollador Backend", 47000.0));
        empleados.add(new Empleado(siguienteId++, "Laura Martín", "Diseñadora UX/UI", 40000.0));
    }

    // Método existente - obtener todos los empleados
    public List<Empleado> obtenerTodosLosEmpleados() {
        return new ArrayList<>(empleados);
    }

    // Método existente - obtener empleado por ID
    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleados.stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(
                        "Empleado con ID " + id + " no encontrado"));
    }

    // Método existente - crear nuevo empleado
    public Empleado crearEmpleado(Empleado empleado) {
        empleado.setId(siguienteId++);
        empleados.add(empleado);
        return empleado;
    }

    // NUEVO MÉTODO - Buscar empleados por puesto
    public List<Empleado> buscarPorPuesto(String puesto) {
        if (puesto == null || puesto.trim().isEmpty()) {
            throw new IllegalArgumentException("El puesto no puede estar vacío");
        }

        List<Empleado> empleadosEncontrados = empleados.stream()
                .filter(empleado -> empleado.getPuesto().toLowerCase()
                        .contains(puesto.toLowerCase()))
                .collect(Collectors.toList());

        return empleadosEncontrados;
    }

    // NUEVO MÉTODO - Eliminar empleado por ID
    public void eliminarEmpleado(Long id) {
        Empleado empleadoAEliminar = empleados.stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(
                        "No se puede eliminar: Empleado con ID " + id + " no encontrado"));

        empleados.remove(empleadoAEliminar);
    }

    // Método auxiliar para verificar si existe un empleado
    public boolean existeEmpleado(Long id) {
        return empleados.stream()
                .anyMatch(empleado -> empleado.getId().equals(id));
    }
}
