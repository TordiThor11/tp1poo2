package sistemaRestaurante;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private HashMap<Consumible, Integer> platosPrincipalesMap = new HashMap<>();
    private HashMap<Consumible, Integer> bebidasMap = new HashMap<>();
    private int numeroMesa;
    private int estado = 0; //0 es en proceso y 1 es confirmado

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public void pedir(PlatoPrincipal plato) {
        if (this.estado == 0) {
            if (platosPrincipalesMap.containsKey(plato)) {
                int cantidadVieja = platosPrincipalesMap.get(plato);
                this.platosPrincipalesMap.replace(plato, cantidadVieja + 1);
            } else {
                this.platosPrincipalesMap.put(plato, 1);
            }
        } else {
            throw new IllegalStateException("No se puede agregar nada una vez confirmado el pedido");
        }
    }

    public void pedir(Bebida bebida) {
        if (this.estado == 0) {
            if (bebidasMap.containsKey(bebida)) {
                int cantidadVieja = bebidasMap.get(bebida);
                this.bebidasMap.replace(bebida, cantidadVieja + 1);
            } else {
                this.bebidasMap.put(bebida, 1);
            }
        } else {
            throw new IllegalStateException("No se puede agregar nada una vez confirmado el pedido");
        }

    }

    public int getCantidadPedido(PlatoPrincipal platoPrincipal) {
        return platosPrincipalesMap.get(platoPrincipal);
    }

    public int getCantidadPedido(Bebida bebida) {
        return bebidasMap.get(bebida);
    }

    public void confirmar() {
        estado = 1;
    }

    public double contarMonto(HashMap<Consumible, Integer> mapa) { //deberia ser private, pero como lo pruebo?
        double sumatoria = 0;
        for (Map.Entry<Consumible, Integer> entry : mapa.entrySet()) {
            sumatoria += entry.getKey().getPrecio() * entry.getValue();
        }
        return sumatoria;
    }

    private double descontarPorPorcentaje(double monto, double porcentaje) {
        return monto * (100.0 - porcentaje) / 100;
    }

    private double agregarPorPorcentaje(double monto, double porcentaje) {
        return monto * (100.0 + porcentaje) / 100;
    }

    public double contarMontoTotal(TarjetaViedma miTarjeta, double porcentajePropina) {
        double sumatoriaTotal = 0;
        sumatoriaTotal += contarMonto(platosPrincipalesMap);
        sumatoriaTotal += contarMonto(bebidasMap);
        sumatoriaTotal = agregarPorPorcentaje(sumatoriaTotal, porcentajePropina);
        return sumatoriaTotal;
    }

    public double contarMontoTotal(TarjetaMastercard miTarjeta, double porcentajePropina) {
        double sumatoriaTotal = 0;
        sumatoriaTotal += contarMonto(platosPrincipalesMap);
        sumatoriaTotal = descontarPorPorcentaje(sumatoriaTotal, 2.0);//Descuento del 2% en platos principales
        sumatoriaTotal += contarMonto(bebidasMap);
        sumatoriaTotal = agregarPorPorcentaje(sumatoriaTotal, porcentajePropina);
        return sumatoriaTotal;
    }

    public double contarMontoTotal(TarjetaComarcaplus miTarjeta, double porcentajePropina) {
        double sumatoriaTotal = 0;
        sumatoriaTotal += contarMonto(platosPrincipalesMap);
        sumatoriaTotal += contarMonto(bebidasMap);
        sumatoriaTotal = descontarPorPorcentaje(sumatoriaTotal, 2.0);//Descuento del 2% en el total
        sumatoriaTotal = agregarPorPorcentaje(sumatoriaTotal, porcentajePropina);
        return sumatoriaTotal;

    }

    public double contarMontoTotal(TarjetaVisa miTarjeta, double porcentajePropina) {
        double sumatoriaTotal = 0;
        sumatoriaTotal += contarMonto(bebidasMap);
        sumatoriaTotal = descontarPorPorcentaje(sumatoriaTotal, 3.0);//Descuento del 3% en bebidas
        sumatoriaTotal += contarMonto(platosPrincipalesMap);
        sumatoriaTotal = agregarPorPorcentaje(sumatoriaTotal, porcentajePropina);
        return sumatoriaTotal;
    }

}
