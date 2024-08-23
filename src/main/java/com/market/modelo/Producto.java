package main.java.com.market.modelo;

import main.java.com.market.excepciones.PrecioUnitarioInvalidoException;
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
    protected Boolean estaDisponibleVenta;
    protected BigDecimal porcentajeDescuento;


    public BigDecimal getPrecioFinal(){
        if (precioUnitario == null || porcentajeGanancia == null){
            throw new ValorInvalidoPrecioFinalException();
        }

        BigDecimal precioDeVenta = precioUnitario
                .multiply(porcentajeGanancia.divide(new BigDecimal(100),2,RoundingMode.HALF_UP)
                        .add(new BigDecimal(1)));

        if ( porcentajeDescuento == null || porcentajeDescuento.equals(new BigDecimal(0))) {
            return precioDeVenta;
        }

        BigDecimal cantidadDescuento = precioDeVenta
                .multiply(porcentajeDescuento)
                .divide(new BigDecimal("100"),2,RoundingMode.HALF_UP);

        BigDecimal precioFinal = precioDeVenta.subtract(cantidadDescuento);
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
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) <= 0 ) {
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
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}