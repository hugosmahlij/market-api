package main.java.com.market.servicio;

import main.java.com.market.excepciones.ExcesoDeStockException;
import main.java.com.market.excepciones.MaximoUnidadesException;
import main.java.com.market.excepciones.MinimoProductosException;
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

    //Metodo para realizar una venta y mostrar detalles

    public void realizarVenta(List<Producto> productosVendidos, List<Integer> cantidadesVendidas, Tienda tienda) {
        if (productosVendidos.size() > 3 ) {
            throw new MinimoProductosException();
        }

        BigDecimal totalVenta = BigDecimal.ZERO;
        boolean hayStockInsuficiente = false;
        boolean hayProductoNoDisponible = false;
        StringBuilder detalleVenta = new StringBuilder();

        for (int i = 0; i < productosVendidos.size(); i++) {
            Producto producto = productosVendidos.get(i);
            int cantidadVendida = cantidadesVendidas.get(i);

            if(cantidadVendida > 12) {
                throw new MaximoUnidadesException();
            }

            if (!producto.getEstaDisponibleVenta()) {
                detalleVenta.append("El producto").append(producto.getId()).append(producto.getDescripcion()).append("no se encuentra disponible");
                hayProductoNoDisponible = true;
            }

            int stockDisponible = producto.getStock();

            if(cantidadVendida > stockDisponible) {
                detalleVenta.append("Hay productos con stock disponible menor al solicitado.");
                cantidadVendida = stockDisponible;
                producto.setEstaDisponibleVenta(false);
                hayStockInsuficiente = true;
            }

            BigDecimal precioVenta = obtenerPrecioFinalConReglas(producto).multiply(new BigDecimal(cantidadVendida));
            detalleVenta.append(producto.getId()).append(producto.getDescripcion()).append(cantidadVendida).append("x");

            totalVenta = totalVenta.add(precioVenta);

            producto.setStock(stockDisponible - cantidadVendida);
            if(producto.getStock() == 0 ) {
                producto.setEstaDisponibleVenta(false);
            }
        }

        if (hayProductoNoDisponible || hayStockInsuficiente) {
            System.out.println(detalleVenta.toString());
        }

        System.out.println("TOTAL VENTA:" + totalVenta);
    }
}
