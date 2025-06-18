public class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Fase de Desarrollo: " + faseDesarrollo);
    }
}
