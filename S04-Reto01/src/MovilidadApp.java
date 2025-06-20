import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MovilidadApp {

    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                TimeUnit.SECONDS.sleep(2 + ThreadLocalRandom.current().nextInt(2));
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al calcular la ruta");
            }
        });
    }

    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                TimeUnit.SECONDS.sleep(1 + ThreadLocalRandom.current().nextInt(2));
                return 75.50;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al estimar la tarifa");
            }
        });
    }

    public void procesarSolicitud() {
        calcularRuta().thenCombine(estimarTarifa(), (ruta, tarifa) ->
                "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
        ).exceptionally(ex ->
                "❌ Error en la solicitud: " + ex.getMessage()
        ).thenAccept(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        MovilidadApp app = new MovilidadApp();
        app.procesarSolicitud();

        // Espera suficiente para que las tareas async terminen
        TimeUnit.SECONDS.sleep(5);
    }
}
