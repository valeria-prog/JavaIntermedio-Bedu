import java.util.List;

public class GestionOrdenes {

    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
            System.out.println("---");
        }
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                OrdenPersonalizada orden = (OrdenPersonalizada) obj;
                // Aquí simulamos el ajuste y mostramos mensaje
                System.out.println("✅ Orden " + orden.codigo + " ajustada con costo adicional de $" + costoAdicional);
            }
        }
    }

    public static void mostrarResumen(List<? extends OrdenProduccion> masas, List<? extends OrdenProduccion> personalizadas, List<? extends OrdenProduccion> prototipos) {
        System.out.println("Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + masas.size());
        System.out.println("🛠️ Personalizadas: " + personalizadas.size());
        System.out.println("🧪 Prototipos: " + prototipos.size());
    }
}
