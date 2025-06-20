import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AeropuertoControl {

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🛫 Verificando disponibilidad de pista...");
                TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
                return true; // Cambia a false para simular condiciones no óptimas
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar pista");
            }
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🌦️ Verificando condiciones meteorológicas...");
                TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar clima");
            }
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("✈️ Verificando tráfico aéreo cercano...");
                TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar tráfico aéreo");
            }
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🧍 Verificando disponibilidad de personal en tierra...");
                TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar personal de tierra");
            }
        });
    }

    public void gestionarAterrizaje() {
        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(pista, clima, trafico, personal);

        allChecks.thenRun(() -> {
            try {
                boolean condicionesOk = pista.get() && clima.get() && trafico.get() && personal.get();
                if (condicionesOk) {
                    System.out.println("🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                } else {
                    System.out.println("🚫 Aterrizaje denegado: condiciones no óptimas.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error al procesar verificaciones: " + e.getMessage());
            }
        }).exceptionally(ex -> {
            System.out.println("❌ Error en las verificaciones asincrónicas: " + ex.getMessage());
            return null;
        });
    }

    public static void main(String[] args) throws InterruptedException {
        AeropuertoControl control = new AeropuertoControl();
        control.gestionarAterrizaje();

        // Espera para permitir que los procesos async finalicen
        TimeUnit.SECONDS.sleep(8);
    }
}
