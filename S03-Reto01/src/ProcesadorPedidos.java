import java.util.*;
import java.util.stream.*;

public class ProcesadorPedidos {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Juan Pérez", "domicilio", "555-1234"),
                new Pedido("María López", "local", "555-0000"),
                new Pedido("Carlos Ruiz", "domicilio", null),
                new Pedido("Ana Torres", "domicilio", "555-5678")
        );

        System.out.println("📞 Confirmando pedidos a domicilio:");

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
                .map(Pedido::getTelefono)
                .flatMap(Optional::stream)
                .map(tel -> "📞 Confirmación enviada al número: " + tel)
                .forEach(System.out::println);
    }
}

