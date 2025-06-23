package com.meridian.meridianprime;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class MeridianPrimeApplication {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("🌍 Iniciando monitoreo de sistemas críticos en Meridian Prime...");

        AtomicInteger alertaGlobalCounter = new AtomicInteger(0);

        Flux<String> trafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 101))
                .filter(c -> c > 70)
                .map(c -> "🚗 Alerta: Congestión del " + c + "% en Avenida Solar")
                .onBackpressureBuffer();

        Flux<String> contaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> ThreadLocalRandom.current().nextInt(10, 101))
                .filter(pm -> pm > 50)
                .map(pm -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)");

        List<String> prioridades = Arrays.asList("Baja", "Media", "Alta");
        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> prioridades.get(ThreadLocalRandom.current().nextInt(prioridades.size())))
                .filter(p -> p.equals("Alta"))
                .map(p -> "🚑 Emergencia vial: Accidente con prioridad Alta");

        Flux<String> trenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 11))
                .filter(min -> min > 5)
                .map(min -> "🚝 Tren maglev con retraso crítico: " + min + " minutos")
                .onBackpressureBuffer();

        List<String> luces = Arrays.asList("Verde", "Amarillo", "Rojo");
        AtomicInteger rojosSeguidos = new AtomicInteger(0);
        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> luces.get(ThreadLocalRandom.current().nextInt(luces.size())))
                .flatMap(color -> {
                    if (color.equals("Rojo")) {
                        if (rojosSeguidos.incrementAndGet() >= 3) {
                            rojosSeguidos.set(0);
                            return Flux.just("🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte");
                        }
                    } else {
                        rojosSeguidos.set(0);
                    }
                    return Flux.empty(); // ✅ En vez de return null
                });

        Flux.merge(trafico, contaminacion, accidentes, trenes, semaforos)
                .publishOn(Schedulers.parallel())
                .doOnNext(msg -> {
                    System.out.println(msg);
                    if (alertaGlobalCounter.incrementAndGet() >= 3) {
                        System.out.println("\n🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime\n");
                        alertaGlobalCounter.set(0);
                    }
                })
                .subscribe();

        Thread.sleep(15000); // Simulación por 15 segundos
    }
}

