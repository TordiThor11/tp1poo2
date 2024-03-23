package sistemaRestaurante;

abstract class Tarjeta {
    private int numeroTarjeta;
    private double saldo;

    public Tarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Tarjeta(int numeroTarjeta, double saldo) {
        this(numeroTarjeta);
        this.saldo = saldo;
    }

    public void pagar(double monto) throws IllegalStateException {
        if (this.saldo - monto >= 0) {
            this.saldo -= monto;
        } else {
            throw new IllegalStateException("Saldo insuficiente para realizar la transaccion");
        }
    }

    public double getSaldo() {
        return this.saldo;
    }
}
