package main.java.com.market.modelo;

import main.java.com.market.excepciones.ValorInvalidoException;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class Producto {
    protected String id;
    protected String descripcion;
    protected Integer stock;
    protected BigDecimal precioUnitario;
    protected BigDecimal porcentajeGanancia;
    protected Boolean estaDisponibleVenta;
    protected BigDecimal porcentajeDescuento;

    public BigDecimal getPrecioFinal(){
        BigDecimal cantidadDescuento = precioUnitario.multiply(porcentajeDescuento).divide(new BigDecimal("100"),2,RoundingMode.HALF_UP);
        BigDecimal precioFinal = precioUnitario.subtract(cantidadDescuento);
        return precioFinal.setScale(2, RoundingMode.HALF_UP);
    }


    public void setId(String id) {
        if (id != null && id.length() > 5) {
            throw new ValorInvalidoException("El id del producto no puede contener más de 5 caracteres.");
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
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
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
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}