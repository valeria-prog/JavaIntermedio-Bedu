import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Lista de videos
        List<Video> videos = new ArrayList<>();
        videos.add(new Video("Introducción a Java", "Mario", 15));
        videos.add(new Video("POO en Java", "Carlos", 20));

        // Lista de artículos
        List<Articulo> articulos = new ArrayList<>();
        articulos.add(new Articulo("Historia de Java", "Ana", 1200));
        articulos.add(new Articulo("Tipos de datos", "Luis", 800));

        // Lista de ejercicios
        List<Ejercicio> ejercicios = new ArrayList<>();
        ejercicios.add(new Ejercicio("Variables y tipos", "Luis"));
        ejercicios.add(new Ejercicio("Condicionales", "Mario"));

        // Mostrar todos los materiales
        System.out.println("📚 Materiales del curso:");
        System.out.println("--- Videos ---");
        GestionMaterial.mostrarMateriales(videos);
        System.out.println("\n--- Artículos ---");
        GestionMaterial.mostrarMateriales(articulos);
        System.out.println("\n--- Ejercicios ---");
        GestionMaterial.mostrarMateriales(ejercicios);

        // Contar duración de videos
        GestionMaterial.contarDuracionVideos(videos);

        // Marcar ejercicios como revisados
        System.out.println("\n✅ Marcando ejercicios como revisados...");
        GestionMaterial.marcarEjerciciosRevisados(ejercicios);

        // Mostrar ejercicios actualizados
        System.out.println("\n--- Ejercicios actualizados ---");
        GestionMaterial.mostrarMateriales(ejercicios);

        // Filtrar materiales por autor
        System.out.println("\n🔍 Filtrar materiales por autor: Mario");
        List<MaterialCurso> todosMateriales = new ArrayList<>();
        todosMateriales.addAll(videos);
        todosMateriales.addAll(articulos);
        todosMateriales.addAll(ejercicios);

        GestionMaterial.filtrarPorAutor(todosMateriales, m -> m.autor.equals("Mario"));
    }
}
