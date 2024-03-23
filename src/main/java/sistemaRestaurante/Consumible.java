package sistemaRestaurante;

abstract class Consumible {
    private String nombre;
    private double precio;

    public Consumible(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}
