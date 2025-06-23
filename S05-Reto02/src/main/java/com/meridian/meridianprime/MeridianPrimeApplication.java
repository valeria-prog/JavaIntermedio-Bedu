package com.meridian.meridianprime;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class MeridianPrimeApplication {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🏥 Iniciando monitoreo de pacientes críticos en UCI...");

        Flux<String> paciente1 = simularPaciente(1);
        Flux<String> paciente2 = simularPaciente(2);
        Flux<String> paciente3 = simularPaciente(3);

        Flux.merge(paciente1, paciente2, paciente3)
            .delayElements(Duration.ofSeconds(1)) // Backpressure: 1 alerta por segundo
            .subscribe(System.out::println);

        Thread.sleep(20000); // Simulación por 20 segundos
    }

    private static Flux<String> simularPaciente(int id) {
        return Flux.interval(Duration.ofMillis(300))
                .flatMap(i -> {
                    String alerta = generarSignosVitales(id);
                    return alerta != null ? Flux.just(alerta) : Flux.empty();
                });
    }

    private static String generarSignosVitales(int pacienteId) {
        int fc = ThreadLocalRandom.current().nextInt(40, 131); // Frecuencia cardíaca
        int paSist = ThreadLocalRandom.current().nextInt(80, 151); // Presión sistólica
        int paDiast = ThreadLocalRandom.current().nextInt(50, 101); // Presión diastólica
        int spo2 = ThreadLocalRandom.current().nextInt(85, 101); // Oxígeno

        if (fc < 50 || fc > 120) {
            return "⚠️ Paciente " + pacienteId + " - FC crítica: " + fc + " bpm";
        } else if (spo2 < 90) {
            return "⚠️ Paciente " + pacienteId + " - SpO2 baja: " + spo2 + "%";
        } else if (paSist < 90 || paSist > 140 || paDiast < 60 || paDiast > 90) {
            return "⚠️ Paciente " + pacienteId + " - PA crítica: " + paSist + "/" + paDiast + " mmHg";
        }
        return null; // No hay alerta crítica
    }
}
