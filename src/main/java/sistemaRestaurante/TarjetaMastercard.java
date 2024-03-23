package sistemaRestaurante;

public class TarjetaMastercard extends Tarjeta {
    public TarjetaMastercard(int numeroTarjeta) {
        super(numeroTarjeta);
    }

    public TarjetaMastercard(int numeroTarjeta, double saldo) {
        super(numeroTarjeta, saldo);
    }
}
