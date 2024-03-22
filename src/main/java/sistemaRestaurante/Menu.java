package sistemaRestaurante;

import java.util.ArrayList;

public class Menu {
    private ArrayList<OpcionesDeMenu> platosPrincipales = new ArrayList<OpcionesDeMenu>();
    private ArrayList<OpcionesDeMenu> bebidas = new ArrayList<OpcionesDeMenu>();
    public Menu(){
        platosPrincipales.add(new OpcionesDeMenu("napolitana", 250));
        platosPrincipales.add(new OpcionesDeMenu("ravioles", 300));
        bebidas.add(new OpcionesDeMenu("cocacola", 75));
        bebidas.add(new OpcionesDeMenu("sprite", 70));
    }

}
