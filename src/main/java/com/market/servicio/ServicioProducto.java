package main.java.com.market.servicio;

import main.java.com.market.excepciones.*;
import main.java.com.market.modelo.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServicioProducto {

    // Metodo para agregar productos a la tienda
    public void agregarProductoATienda(Producto producto, Tienda tienda) {
        //Verificar que el producto o la tienda no sean nulos
        if (producto == null || tienda == null) {
            throw new NoPuedenSerNulosException();
        }

        //Calcular el costo total de los productos
        BigDecimal costoTotalProductos = producto.getPrecioUnitario().multiply(new BigDecimal(producto.getStock()));
        Double saldoDeCaja = tienda.getSaldoCaja() - costoTotalProductos.doubleValue();

        //Verificar exceso de stock
        if (tienda.stockTotalProductos() + producto.getStock() > tienda.getMaximoProductosStock()) {
            throw new ExcesoDeStockException();
        }

        //Verificar saldo insuficiente
        if (saldoDeCaja < 0) {
            throw new SaldoInsuficienteException();
        }

        //Actualizar el saldo de la caja y agregar el producto
        tienda.setSaldoCaja(saldoDeCaja);
        producto.setEstaDisponibleVenta(true);
        tienda.getListaProductos().add(producto);
    }

    //Metodo para calcular las calorías de las bebidas
    private Integer calcularCalorias(ProductoBebida bebida) {
        Double graduacion = bebida.getGraduacionAlcohol();
        Integer calorias = bebida.getCalorias();

        if (graduacion == null || calorias == null) {
            throw new GraduacionOCaloriasException();
        }

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

        if (producto == null) {
            throw new ProductoNuloExcepetion();
        }

        BigDecimal precioFinal = producto.getPrecioFinal();

        if (precioFinal == null) {
            throw new ValorInvalidoPrecioFinalException();
        }

        //Aplicar el descuento segun el tipo de producto
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

        //Aplicar descuento comun
        BigDecimal porcentajeDescuento = producto.getPorcentajeDescuento();
        if (porcentajeDescuento != null) {
            BigDecimal descuento = precioFinal.multiply(producto.getPorcentajeDescuento().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
            precioFinal = precioFinal.subtract(descuento);
        }

        //Aplicar impuesto a productos importados
        if (producto instanceof ProductoEnvasado && ((ProductoEnvasado) producto).getEsImportado()) {
            BigDecimal impuesto = precioFinal.multiply(new BigDecimal("0.12"));
            precioFinal = precioFinal.add(impuesto);
        }

        return precioFinal.setScale(2, RoundingMode.HALF_UP);
    }

    //Metodo para realizar una venta y mostrar detalles
    public void realizarVenta(List<Producto> productosVendidos, List<Integer> cantidadesVendidas, Tienda tienda) {
        if (productosVendidos == null || cantidadesVendidas == null || tienda == null) {
            throw new ParametrosDeVentaException();
        }

        if (productosVendidos.size() != cantidadesVendidas.size()) {
            throw new IllegalArgumentException("El tamaño de productosVendidos y cantidadesVendidas debe ser igual");
        }

        if (productosVendidos.size() > 3) {
            throw new MinimoProductosException();
        }

        BigDecimal totalVenta = BigDecimal.ZERO;
        boolean hayStockInsuficiente = false;
        boolean hayProductoNoDisponible = false;
        StringBuilder detalleVenta = new StringBuilder();

        for (int i = 0; i < productosVendidos.size(); i++) {
            Producto producto = productosVendidos.get(i);
            int cantidadVendida = cantidadesVendidas.get(i);

            if (cantidadVendida > 12) {
                throw new MaximoUnidadesException();
            }

            Boolean estaDisponible = producto.getEstaDisponibleVenta();
            if (estaDisponible == null || !estaDisponible) {
                detalleVenta.append("El producto").append(producto.getId()).append(producto.getDescripcion()).append("no se encuentra disponible");
                hayProductoNoDisponible = true;
                continue;
            }

            int stockDisponible = producto.getStock();

            if (cantidadVendida > stockDisponible) {
                detalleVenta.append("Hay productos con stock disponible menor al solicitado.");
                cantidadVendida = stockDisponible;
                producto.setEstaDisponibleVenta(false);
                hayStockInsuficiente = true;
            }

            BigDecimal precioVenta = obtenerPrecioFinalConReglas(producto).multiply(new BigDecimal(cantidadVendida));
            detalleVenta.append(producto.getId()).append(producto.getDescripcion()).append(cantidadVendida).append("x");

            totalVenta = totalVenta.add(precioVenta);

            producto.setStock(stockDisponible - cantidadVendida);
            if (producto.getStock() == 0) {
                producto.setEstaDisponibleVenta(false);
            }
        }

        if (hayProductoNoDisponible || hayStockInsuficiente) {
            System.out.println(detalleVenta.toString());
        }

        System.out.println("TOTAL VENTA:" + totalVenta);
    }

    //Metodo para obtemer los comestibles no importados con descuento menor a un procentaje dado
    public List<String> obtenerComestiblesConMenorDescuento(BigDecimal porcentajeDescuento, Tienda tienda) {
        if (porcentajeDescuento == null || tienda == null) {
            throw new PorcentajeOTiendaNulosException();
        }

        List<String> productosFiltrados = new ArrayList<>();

        for (int i = 0; i < tienda.getListaProductos().size(); i++) {
            Producto producto = tienda.getListaProductos().get(i);

            if (producto instanceof ProductoEnvasado) {
                ProductoEnvasado comestible = (ProductoEnvasado) producto;

                //Verificar si el producto no es importado y su descuento es menor al porcentaje dado
                if (!comestible.getEsImportado() && comestible.getPorcentajeDescuento().compareTo(porcentajeDescuento) < 0) {
                    productosFiltrados.add(comestible.getDescripcion().toUpperCase());
                }
            }
        }

        //Ordenar la lista de productos por precio de venta ascendente
        productosFiltrados.sort(new Comparator<String>() {
            @Override
            public int compare(String producto1, String producto2) {
                BigDecimal precioProducto1 = tienda.getListaProductos().stream().filter(p -> p.getDescripcion().equalsIgnoreCase(producto1)).findFirst().map(Producto::getPrecioFinal).orElse(BigDecimal.ZERO);
                BigDecimal precioProducto2 = tienda.getListaProductos().stream().filter(p -> p.getDescripcion().equalsIgnoreCase(producto2)).findFirst().map(Producto::getPrecioFinal).orElse(BigDecimal.ZERO);

                return precioProducto1.compareTo(precioProducto2);
            }
        });

        return productosFiltrados;
    }

    //Metodo para mostrar el stock y saldo después de cada operación
    public void mostrarStockYSaldo(Tienda tienda) {
        if (tienda == null) {
            throw new MostrarStockTiendaException();
        }

        System.out.println("Saldo de la caja: " + tienda.getSaldoCaja());
        System.out.println("Stock de productos:");
        for (Producto producto : tienda.getListaProductos()) {
            System.out.println("Producto: " + producto.getDescripcion() + " Stock: " + producto.getStock());
        }
    }
}
