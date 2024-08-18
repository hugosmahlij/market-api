package main.java.com.market.servicio;

import main.java.com.market.excepciones.SaldoInsuficienteException;
import main.java.com.market.modelo.Producto;
import main.java.com.market.modelo.Tienda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServicioProducto {

    public void agregarProductoATienda(Producto producto, Tienda tienda) {
        BigDecimal costoTotalProductos = producto.getPrecioUnitario().multiply(new BigDecimal(producto.getStock()));
        Double saldoDeCaja = tienda.getSaldoCaja() - costoTotalProductos.doubleValue();
        if(producto.getStock() > ) //TODO VALIDAR EL STOCK NO EXCEDA EL MAXIMO

        if (saldoDeCaja < 0) {
            throw new SaldoInsuficienteException();
        }
        tienda.setSaldoCaja(saldoDeCaja);

        producto.setEstaDisponibleVenta(true);
        tienda.getListaProductos().add(producto);
    }
}
