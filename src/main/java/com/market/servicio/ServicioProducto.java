package main.java.com.market.servicio;

import main.java.com.market.excepciones.ExcesoDeStockException;
import main.java.com.market.excepciones.SaldoInsuficienteException;
import main.java.com.market.modelo.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    //Metodo para calcular el precio final considerando impuestos y restricciones
    private BigDecimal obtenerPrecioFinalConReglas(Producto producto) {
        BigDecimal precioFinal = producto.getPrecioFinal();

        if (producto instanceof ProductoBebida) {
            ProductoBebida bebida = (ProductoBebida) producto;
            if (bebida.getPorcentajeDescuento().compareTo(new BigDecimal("10")) > 0) {
                bebida.setPorcentajeDescuento(new BigDecimal("10"));
            }
        } else if (producto instanceof ProductoEnvasado) {
            ProductoEnvasado envasado = (ProductoEnvasado) producto;
            if (producto.getPorcentajeDescuento().compareTo(new BigDecimal("15")) > 0) {
                envasado.setPorcentajeDescuento(new BigDecimal("15"));
            }
        } else if (producto instanceof ProductoLimpieza) {
            ProductoLimpieza limpieza = (ProductoLimpieza) producto;
            if (limpieza.getPorcentajeDescuento().compareTo(new BigDecimal("20")) > 0) {
                limpieza.setPorcentajeDescuento(new BigDecimal("20"));
            }
        }

        if (producto.getPorcentajeDescuento() != null) {
            BigDecimal descuento = precioFinal.multiply(producto.getPorcentajeDescuento().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
            precioFinal = precioFinal.subtract(descuento);
        }

        if (producto instanceof ProductoEnvasado && ((ProductoEnvasado) producto).getEsImportado()) {
            BigDecimal impuesto = precioFinal.multiply(new BigDecimal("0.12"));
            precioFinal = precioFinal.add(impuesto);
        }

        return precioFinal.setScale(2,RoundingMode.HALF_UP);
    }
}
