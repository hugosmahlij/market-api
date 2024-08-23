package test.java.com.market;

import main.java.com.market.modelo.*;
import main.java.com.market.servicio.ServicioProducto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestTienda {
    public static void main(String[] args) {
        //Creacion de la tiendaKike
        Tienda tiendaKike = new Tienda(new ArrayList<>(), 1000.00,100);

        //Creacion del servicio de productos
        ServicioProducto servicioProducto = new ServicioProducto();

        //Creacion de productos de distintos tipos
        ProductoBebida quilmes = new ProductoBebida();
        quilmes.setId("432");
        quilmes.setDescripcion("Cerveza");
        quilmes.setPrecioUnitario(new BigDecimal("10.00"));
        quilmes.setStock(20);
        quilmes.setCalorias(150);
        quilmes.setGraduacionAlcohol(5.0);
        quilmes.setPorcentajeDescuento(new BigDecimal("5"));
        quilmes.setEsImportado(false);

        ProductoEnvasado marolio = new ProductoEnvasado();
        marolio.setId("534");
        marolio.setDescripcion("Aceite");
        marolio.setPrecioUnitario(new BigDecimal("50.00"));
        marolio.setStock(8);
        marolio.setCalorias(300);
        marolio.setTipoEnvase("Botella");
        marolio.setPorcentajeDescuento(new BigDecimal("10"));
        marolio.setEsImportado(true);

        ProductoLimpieza magistral = new ProductoLimpieza();
        magistral.setId("654");
        magistral.setDescripcion("Detergente");
        magistral.setPrecioUnitario(new BigDecimal("14.00"));
        magistral.setStock(3);
        magistral.setTipoAplicacion(TipoAplicacionLimpieza.COCINA);
        magistral.setPorcentajeDescuento(new BigDecimal("8"));

        //Agregar productos a la tiendaKike
        servicioProducto.agregarProductoATienda(quilmes, tiendaKike);
        servicioProducto.agregarProductoATienda(marolio, tiendaKike);
        servicioProducto.agregarProductoATienda(magistral, tiendaKike);

        //Mostrar stock y saldo de caja luego de agregar productos
        servicioProducto.mostrarStockYSaldo(tiendaKike);

        //Realizar una venta
        List<Producto> productosVendidos = Arrays.asList(quilmes,marolio);
        List<Integer> cantidadesVendidas = Arrays.asList(10, 2);

        servicioProducto.realizarVenta(productosVendidos,cantidadesVendidas,tiendaKike);

        //Mostrar stock y saldo luego de la venta
        servicioProducto.mostrarStockYSaldo(tiendaKike);

        //Obtener productos comestibles no importados con descuento menor a un porcentaje
        BigDecimal porcentajeDescuento = new BigDecimal("10");
        List<String> comestibleConMenorDescuento = servicioProducto.obtenerComestiblesConMenorDescuento(porcentajeDescuento, tiendaKike);

        //Imprimir los resultados obtenidos de producto NO import con descuento
        System.out.println("Comestibles con menor descuento (menos del " + porcentajeDescuento + "%):");
        comestibleConMenorDescuento.forEach(System.out::println);
    }
}
