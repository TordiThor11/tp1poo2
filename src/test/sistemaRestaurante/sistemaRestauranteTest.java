package sistemaRestaurante;

import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class sistemaRestauranteTest {
    //inicializar platos
    PlatoPrincipal napolitana = new PlatoPrincipal("napolitana", 250);
    PlatoPrincipal ravioles = new PlatoPrincipal("ravioles", 220);
    Bebida cocacola = new Bebida("cocacola", 75);
    Bebida sprite = new Bebida("sprite", 60);

    @Test
    public void ordenarPrimerPlato() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(ravioles);
        assertEquals(miPedido.getCantidadPedido(ravioles), 1);
    }

    @Test
    public void ordenarMasPlatos() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(ravioles);
        int cantidadPedidos = miPedido.getCantidadPedido(ravioles);
        miPedido.pedir(ravioles);
        assertEquals(miPedido.getCantidadPedido(ravioles), cantidadPedidos + 1);
    }

    @Test
    public void ordenarPrimerBebida() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(sprite);
        assertEquals(miPedido.getCantidadPedido(sprite), 1);
    }

    @Test
    public void ordenarMasBebidas() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(sprite);
        int cantidadPedidos = miPedido.getCantidadPedido(sprite);
        miPedido.pedir(sprite);
        assertEquals(miPedido.getCantidadPedido(sprite), cantidadPedidos + 1);
    }

    @Test
    public void contarMonto() {
        HashMap<Consumible, Integer> platosPrincipalesMap = new HashMap<>();
        platosPrincipalesMap.put(napolitana, 2);
        platosPrincipalesMap.put(ravioles, 1);

        Pedido miPedido = new Pedido(1);

        assertEquals(720, miPedido.contarMonto(platosPrincipalesMap)); //contarMonto deberia ser private, pero como lo pruebo?
    }

    @Test
    public void pagarConTarjetaViedma() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.pedir(ravioles);
        miPedido.pedir(sprite);
        TarjetaViedma miTarjeta = new TarjetaViedma(33142, 1000);
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta, 3.0));   //ejemplo como porcentaje de 3%
        assertEquals(454.1, miTarjeta.getSaldo());
    }

    @Test
    public void pagarConTarjetaVisa() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.pedir(ravioles);
        miPedido.pedir(sprite);
        TarjetaVisa miTarjeta = new TarjetaVisa(33142, 1000);
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta, 5)); //ejemplo de propina con 5%
        assertEquals(445.3899999999999, miTarjeta.getSaldo());
    }

    @Test
    public void pagarConTarjetaMastercard() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.pedir(ravioles);
        miPedido.pedir(sprite);
        TarjetaMastercard miTarjeta = new TarjetaMastercard(33142, 1000);
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta, 2)); //Porcentaje de ejemplo del 2%
        assertEquals(468.98799999999994, miTarjeta.getSaldo());
    }

    @Test
    public void pagarConTarjetaComarcaplus() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.pedir(ravioles);
        miPedido.pedir(sprite);
        TarjetaComarcaplus miTarjeta = new TarjetaComarcaplus(33142, 1000);
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta, 2));    //Porcentaje de ejemplo del 2%
        assertEquals(470.212, miTarjeta.getSaldo());
    }

    @Test
    public void pedirComidaCuandoEstaConfirmado() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.confirmar();
        assertThrows(IllegalStateException.class, () -> {
            miPedido.pedir(napolitana);
        });

    }

    @Test
    public void pedirBebidaCuandoEstaConfirmado() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(cocacola);
        miPedido.confirmar();
        assertThrows(IllegalStateException.class, () -> {
            miPedido.pedir(cocacola);
        });

    }
}
