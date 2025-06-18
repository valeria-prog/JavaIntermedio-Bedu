import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        // Listas
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        List<OrdenPrototipo> ordenesPrototipos = new ArrayList<>();

        // Agregar órdenes en masa
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        // Agregar órdenes personalizadas
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        // Agregar órdenes prototipo
        ordenesPrototipos.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipos.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Mostrar todas las órdenes
        System.out.println("🔋 Órdenes registradas:");
        System.out.println("📋 Órdenes en masa:");
        GestionOrdenes.mostrarOrdenes(ordenesMasa);
        System.out.println("📋 Órdenes personalizadas:");
        GestionOrdenes.mostrarOrdenes(ordenesPersonalizadas);
        System.out.println("📋 Órdenes prototipo:");
        GestionOrdenes.mostrarOrdenes(ordenesPrototipos);

        // Contar órdenes
        int totalMasa = ordenesMasa.size();
        int totalPersonalizadas = ordenesPersonalizadas.size();
        int totalPrototipos = ordenesPrototipos.size();

        // Procesar órdenes personalizadas
        System.out.println("\n💰 Procesando órdenes personalizadas...");
        int costoAdicional = 200;
        GestionOrdenes.procesarPersonalizadas(ordenesPersonalizadas, costoAdicional);

        // Mostrar resumen total
        System.out.println();
        GestionOrdenes.mostrarResumen(ordenesMasa, ordenesPersonalizadas, ordenesPrototipos);
    }
}
