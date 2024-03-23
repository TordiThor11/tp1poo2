package sistemaRestaurante;

import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

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
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta));
        assertEquals(470, miTarjeta.getSaldo());
    }

    @Test
    public void pagarConTarjetaVisa() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.pedir(ravioles);
        miPedido.pedir(sprite);
        TarjetaVisa miTarjeta = new TarjetaVisa(33142, 1000);
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta));
        assertEquals(471.79999999999995, miTarjeta.getSaldo());
    }

    @Test
    public void pagarConTarjetaMastercard() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.pedir(ravioles);
        miPedido.pedir(sprite);
        TarjetaMastercard miTarjeta = new TarjetaMastercard(33142, 1000);
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta));
        assertEquals(479.4, miTarjeta.getSaldo());
    }

    @Test
    public void pagarConTarjetaComarcaplus() {
        Pedido miPedido = new Pedido(1);
        miPedido.pedir(napolitana);
        miPedido.pedir(ravioles);
        miPedido.pedir(sprite);
        TarjetaComarcaplus miTarjeta = new TarjetaComarcaplus(33142, 1000);
        miTarjeta.pagar(miPedido.contarMontoTotal(miTarjeta));
        assertEquals(480.6, miTarjeta.getSaldo());
    }
}
