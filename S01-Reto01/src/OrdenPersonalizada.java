public class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Cliente: " + cliente);
    }
}

