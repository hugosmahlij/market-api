package main.java.com.market.modelo;

import main.java.com.market.excepciones.ValorInvalidoException;
import org.jetbrains.annotations.NotNull;

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
    protected Integer idTienda;

    public BigDecimal getPrecioFinal(){
        BigDecimal cantidadDescuento = precioUnitario.multiply(porcentajeDescuento).divide(new BigDecimal("100"),2,RoundingMode.HALF_UP);
        BigDecimal precioFinal = precioUnitario.subtract(cantidadDescuento);
        return precioFinal.setScale(2, RoundingMode.HALF_UP);
    }


    public void setId(@NotNull String id) {
        if (id.length() > 5) {
            throw new ValorInvalidoException("El valor ingresado no puede contener más de 3 caracteres.");
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

    public Integer getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }
}