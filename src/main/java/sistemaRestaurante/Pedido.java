package sistemaRestaurante;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private HashMap<Consumible, Integer> platosPrincipalesMap = new HashMap<>();
    private HashMap<Consumible, Integer> bebidasMap = new HashMap<>();
    private int numeroMesa;
    private int estado = 0; //0 es en proceso, 1 es confirmado y 2 es pagado

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public void pedir(PlatoPrincipal plato) {
        if (platosPrincipalesMap.containsKey(plato)) {
            int cantidadVieja = platosPrincipalesMap.get(plato);
            this.platosPrincipalesMap.replace(plato, cantidadVieja + 1);
        } else {
            this.platosPrincipalesMap.put(plato, 1);
        }
    }

    public void pedir(Bebida bebida) {
        if (bebidasMap.containsKey(bebida)) {
            int cantidadVieja = bebidasMap.get(bebida);
            this.bebidasMap.replace(bebida, cantidadVieja + 1);
        } else {
            this.bebidasMap.put(bebida, 1);
        }
    }

    public int getCantidadPedido(PlatoPrincipal platoPrincipal) {
        return platosPrincipalesMap.get(platoPrincipal);
    }

    public int getCantidadPedido(Bebida bebida) {
        return bebidasMap.get(bebida);
    }

    public void confirmar() { //Debo implementar que no se pueda pedir si esta en estado '1'
        estado = 1;
    }

    public double contarMonto(HashMap<Consumible, Integer> mapa) { //deberia ser private, pero como lo pruebo?
        double sumatoria = 0;
        for (Map.Entry<Consumible, Integer> entry : mapa.entrySet()) {
            sumatoria += entry.getKey().getPrecio() * entry.getValue();
        }
        return sumatoria;
    }

    public double contarMontoTotal() {
        double sumatoriaTotal = 0;
        sumatoriaTotal += contarMonto(platosPrincipalesMap);
        sumatoriaTotal += contarMonto(bebidasMap);
        return sumatoriaTotal;
    }

    public void documentarPago() {
        estado = 2;
    }

}
