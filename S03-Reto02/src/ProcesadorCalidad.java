import java.util.*;
import java.util.stream.*;

public class ProcesadorCalidad {
    public static void main(String[] args) {
        List<Sucursal> sucursales = Arrays.asList(
                new Sucursal("Centro", Arrays.asList(
                        new Encuesta("Luis Pérez", "El tiempo de espera fue largo", 2),
                        new Encuesta("Ana Gómez", null, 4)
                )),
                new Sucursal("Norte", Arrays.asList(
                        new Encuesta("Pedro Ruiz", "La atención fue buena, pero la limpieza puede mejorar", 3),
                        new Encuesta("Carla Díaz", null, 5)
                ))
        );

        System.out.println("📋 Procesando comentarios de pacientes insatisfechos:");

        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(encuesta -> encuesta.getCalificacion() <= 3)
                                .map(Encuesta::getComentario)
                                .flatMap(Optional::stream)
                                .map(comentario -> "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + comentario + "\"")
                )
                .forEach(System.out::println);
    }
}
