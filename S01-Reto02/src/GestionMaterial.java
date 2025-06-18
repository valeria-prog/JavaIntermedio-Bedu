import java.util.List;
import java.util.function.Predicate;

public class GestionMaterial {

    // Mostrar todos los materiales (sin importar el tipo)
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // Contar y mostrar duración total de videos
    public static void contarDuracionVideos(List<? extends Video> videos) {
        int totalDuracion = 0;
        for (Video v : videos) {
            totalDuracion += v.getDuracion();
        }
        System.out.println("🎥 Duración total de videos: " + totalDuracion + " minutos");
    }

    // Marcar ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> ejercicios) {
        for (Object obj : ejercicios) {
            if (obj instanceof Ejercicio) {
                Ejercicio e = (Ejercicio) obj;
                e.setRevisado(true);
                System.out.println("✅ Ejercicio '" + e.titulo + "' marcado como revisado.");
            }
        }
    }

    // Filtrar materiales por autor usando Predicate
    public static <T extends MaterialCurso> void filtrarPorAutor(List<T> materiales, Predicate<MaterialCurso> filtro) {
        for (T material : materiales) {
            if (filtro.test(material)) {
                material.mostrarDetalle();
            }
        }
    }
}
