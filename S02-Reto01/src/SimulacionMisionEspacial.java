import java.util.concurrent.*;

public class SimulacionMisionEspacial {
    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> soporte = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> comms = executor.submit(new SistemaComunicaciones());

        System.out.println(comms.get());
        System.out.println(soporte.get());
        System.out.println(termico.get());
        System.out.println(nav.get());

        System.out.println("✅ Todos los sistemas reportan estado operativo.");
        executor.shutdown();
    }
}

