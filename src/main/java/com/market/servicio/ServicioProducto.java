package main.java.com.market.servicio;

import main.java.com.market.excepciones.ExcesoDeStockException;
import main.java.com.market.excepciones.SaldoInsuficienteException;
import main.java.com.market.modelo.Producto;
import main.java.com.market.modelo.ProductoBebida;
import main.java.com.market.modelo.Tienda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServicioProducto {

    // Metodo para agregar productos a la tienda
    public void agregarProductoATienda(Producto producto, Tienda tienda) {
        BigDecimal costoTotalProductos = producto.getPrecioUnitario().multiply(new BigDecimal(producto.getStock()));
        Double saldoDeCaja = tienda.getSaldoCaja() - costoTotalProductos.doubleValue();
        if (tienda.stockTotalProductos() + producto.getStock() > tienda.getMaximoProductosStock()) {
            throw new ExcesoDeStockException();
        }

        if (saldoDeCaja < 0) {
            throw new SaldoInsuficienteException();
        }

        tienda.setSaldoCaja(saldoDeCaja);
        producto.setEstaDisponibleVenta(true);
        tienda.getListaProductos().add(producto);
    }

    //Metodo para calcular las calorías de las bebidas
    private Integer calcularCalorias(ProductoBebida bebida) {
        Double graduacion = bebida.getGraduacionAlcohol();
        Integer calorias = bebida.getCalorias();

        if (graduacion <= 2) {
            return calorias;
        } else if (graduacion <= 4.5) {
            return (int) (calorias * 1.25);
        } else {
            return (int) (calorias * 1.5);
        }
    }
}
