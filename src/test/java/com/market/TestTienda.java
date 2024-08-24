package test.java.com.market;

import main.java.com.market.modelo.*;
import main.java.com.market.servicio.ServicioProducto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestTienda {

    public static void main(String[] args) {
        //          PRUEBA DE VENTA CON PRODUCTOS DISPONIBLES
        /*
        // Crear algunos productos
        ProductoBebida quilmes = new ProductoBebida("101", "Cerveza", new BigDecimal("4.00"), 10, 5.0, 150, new BigDecimal("2"));
        ProductoEnvasado marolio = new ProductoEnvasado("542", "Aceite", new BigDecimal("12.40"), 20, false, new BigDecimal("5"));
        ProductoLimpieza magistral = new ProductoLimpieza( "343", "Detergente", new BigDecimal("10.00"), 30 , TipoAplicacionLimpieza.COCINA, new BigDecimal("2") );

        // Crear una tienda
        Tienda tiendaKike = new Tienda(Arrays.asList(quilmes, marolio, magistral), 9000.00, 200);

        // Crear el servicio
        ServicioProducto servicio = new ServicioProducto();

        // Mostrar stock y saldo inicial
        System.out.println("Estado inicial:");
        servicio.mostrarStockYSaldo(tiendaKike);

        // Realizar una venta
        List<Producto> productosVendidos = Arrays.asList(quilmes, marolio);
        List<Integer> cantidadesVendidas = Arrays.asList(2, 2);
        servicio.realizarVenta(productosVendidos, cantidadesVendidas, tiendaKike);

        // Mostrar stock y saldo después de la venta
        System.out.println("Después de la venta:");
        servicio.mostrarStockYSaldo(tiendaKike);*/

        //          PRUEBA DE STOCK INSUFICIENTE
        /*
        ProductoBebida quilmes = new ProductoBebida("101", "Cerveza", new BigDecimal("4.00"), 2, 5.0, 150, new BigDecimal("2")); // stock limitado
        ProductoEnvasado marolio = new ProductoEnvasado("542", "Aceite", new BigDecimal("12.40"), 20, false, new BigDecimal("5"));

        Tienda tiendaKike = new Tienda(Arrays.asList(quilmes, marolio), 9000.00, 200);

        ServicioProducto servicio = new ServicioProducto();

        System.out.println("Estado inicial:");
        servicio.mostrarStockYSaldo(tiendaKike);

        // Realizar una venta con stock insuficiente para 'quilmes'
        List<Producto> productosVendidos = Arrays.asList(quilmes, marolio);
        List<Integer> cantidadesVendidas = Arrays.asList(5, 2); // Intentar vender más de lo disponible

        try {
            servicio.realizarVenta(productosVendidos, cantidadesVendidas, tiendaKike);
        } catch (Exception e) {
            System.out.println("Error durante la venta: " + e.getMessage());
        }

        System.out.println("Después de la venta:");
        servicio.mostrarStockYSaldo(tiendaKike);*/

        //          PRUEBA DE PRODUCTOS NO DISPONIBLES PARA LA VENTA
        /*
        ProductoBebida quilmes = new ProductoBebida("101", "Cerveza", new BigDecimal("4.00"), 100, 5.0, 150, new BigDecimal("2"));
        ProductoEnvasado marolio = new ProductoEnvasado("542", "Aceite", new BigDecimal("12.40"), 20, false, new BigDecimal("5"));
        ProductoLimpieza magistral = new ProductoLimpieza("343", "Detergente", new BigDecimal("10.00"), 30, TipoAplicacionLimpieza.COCINA, new BigDecimal("2"));

        // Marcar productos como no disponibles
        quilmes.setEstaDisponibleVenta(false);
        marolio.setEstaDisponibleVenta(false);

        Tienda tiendaKike = new Tienda(Arrays.asList(quilmes, marolio, magistral), 9000.00, 200);

        ServicioProducto servicio = new ServicioProducto();

        System.out.println("Estado inicial:");
        servicio.mostrarStockYSaldo(tiendaKike);

        // Intentar realizar una venta con productos no disponibles
        List<Producto> productosVendidos = Arrays.asList(quilmes, marolio);
        List<Integer> cantidadesVendidas = Arrays.asList(2, 2);

        try {
            servicio.realizarVenta(productosVendidos, cantidadesVendidas, tiendaKike);
        } catch (Exception e) {
            System.out.println("Error durante la venta: " + e.getMessage());
        }

        System.out.println("Después de la venta:");
        servicio.mostrarStockYSaldo(tiendaKike);*/

        //          PRUEBA DE DESCUENTOS Y CALCULO DE PRECIO FINAL
        /*
        // Crear algunos productos con descuento
        ProductoBebida quilmes = new ProductoBebida("101", "Cerveza", new BigDecimal("4.00"), 100, 5.0, 150, new BigDecimal("15")); // 15% descuento
        ProductoEnvasado marolio = new ProductoEnvasado("542", "Aceite", new BigDecimal("12.40"), 20, false, new BigDecimal("10")); // 10% descuento

        Tienda tiendaKike = new Tienda(Arrays.asList(quilmes, marolio), 9000.00, 200);

        ServicioProducto servicio = new ServicioProducto();

        System.out.println("Estado inicial:");
        servicio.mostrarStockYSaldo(tiendaKike);

        // Realizar una venta
        List<Producto> productosVendidos = Arrays.asList(quilmes, marolio);
        List<Integer> cantidadesVendidas = Arrays.asList(1, 1); // Solo una unidad de cada producto

        servicio.realizarVenta(productosVendidos, cantidadesVendidas, tiendaKike);

        // Mostrar stock y saldo después de la venta
        System.out.println("Después de la venta:");
        servicio.mostrarStockYSaldo(tiendaKike);*/
    }
}
