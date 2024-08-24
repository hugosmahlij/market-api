package main.java.com.market.modelo;

import main.java.com.market.excepciones.PrecioUnitarioInvalidoException;
import main.java.com.market.excepciones.PrecioUnitarioNuloException;
import main.java.com.market.excepciones.ValorInvalidoIdException;
import main.java.com.market.excepciones.ValorInvalidoPrecioFinalException;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Producto {
    protected String id;
    protected String descripcion;
    protected Integer stock;
    protected BigDecimal precioUnitario;
    protected BigDecimal porcentajeGanancia;
    protected Boolean estaDisponibleVenta = true;
    protected BigDecimal porcentajeDescuento;


    public Producto(String id, String descripcion, BigDecimal precioUnitario, Integer stock, BigDecimal porcentajeDescuento) {
        this.id = id;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.porcentajeGanancia = porcentajeGanancia != null ? porcentajeGanancia : BigDecimal.ZERO;
        this.porcentajeDescuento = porcentajeDescuento != null ? porcentajeDescuento : BigDecimal.ZERO;
    }


    public BigDecimal getPrecioFinal() {
        if (precioUnitario == null || porcentajeGanancia == null) {
            System.out.println("precioUnitario: " + precioUnitario);
            System.out.println("porcentajeGanancia: " + porcentajeGanancia);
            throw new ValorInvalidoPrecioFinalException();
        }

        // Calcular el precio de venta base
        BigDecimal porcentajeGananciaDecimal = porcentajeGanancia.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        BigDecimal precioDeVenta = precioUnitario.multiply(porcentajeGananciaDecimal.add(BigDecimal.ONE));

        System.out.println("precioDeVenta: " + precioDeVenta);

        // Verifica si porcentajeDescuento es nulo y calcula el descuento si es necesario
        if (porcentajeDescuento == null || porcentajeDescuento.compareTo(BigDecimal.ZERO) == 0) {
            return precioDeVenta.setScale(2, RoundingMode.HALF_UP);
        }

        // Calcula el descuento
        BigDecimal descuentoDecimal = porcentajeDescuento.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        BigDecimal cantidadDescuento = precioDeVenta.multiply(descuentoDecimal);
        BigDecimal precioFinal = precioDeVenta.subtract(cantidadDescuento);

        // Devuelve el precio final con dos decimales
        return precioFinal.setScale(2, RoundingMode.HALF_UP);
    }


    public void setId(@NotNull String id) {
        if (id.length() > 5) {
            throw new ValorInvalidoIdException();
        }

        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecioUnitario() {
        if (precioUnitario == null) {
            throw new PrecioUnitarioNuloException();
        }
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PrecioUnitarioInvalidoException();
        }
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(BigDecimal porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public Boolean getEstaDisponibleVenta() {
        return estaDisponibleVenta;
    }

    public void setEstaDisponibleVenta(Boolean estaDisponibleVenta) {
        this.estaDisponibleVenta = estaDisponibleVenta;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento != null ? porcentajeDescuento : BigDecimal.ZERO;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}