import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospital {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable draSanchez = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable drGomez = () -> salaCirugia.usar("Dr. Gómez");
        Runnable enfLopez = () -> salaCirugia.usar("Enfermero López");
        Runnable draMorales = () -> salaCirugia.usar("Dra. Morales");

        executor.execute(draSanchez);
        executor.execute(drGomez);
        executor.execute(enfLopez);
        executor.execute(draMorales);

        executor.shutdown();
    }
}

