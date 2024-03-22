package sistemaRestaurante;

import java.util.ArrayList;
import java.util.HashMap;

public class Pedido {
    private ArrayList<OpcionesDeMenu> platosPrincipales;
    private HashMap<String, Integer> cantidadesPlatosPrincipales = new HashMap<>();
    private ArrayList<OpcionesDeMenu> bebidas;
    private HashMap<String, Integer> cantidadesBebidas = new HashMap<>();
    private int numeroMesa;
    public Pedido(int numeroMesa){
        this.numeroMesa = numeroMesa;
    }
   /* public void pedir(OpcionesDeMenu opcionDeMenu, int cantiad){
        this.platosdPrincipales.add(opcionDeMenu);
        this.
    }*/

}
