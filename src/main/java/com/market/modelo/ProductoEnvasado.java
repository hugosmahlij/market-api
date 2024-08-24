package main.java.com.market.modelo;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public final class ProductoEnvasado extends Producto {
    private String tipoEnvase;
    private Boolean esImportado;
    private Date fechaVencimiento;
    private Integer calorias;

    public ProductoEnvasado(String id, String descripcion, BigDecimal precioUnitario, Integer stock, Boolean esImportado, BigDecimal porcentajeDescuento) {
        super(id, descripcion, precioUnitario, stock, porcentajeDescuento);
        this.esImportado = esImportado;
    }

    @Override
    public void setId(@NotNull String id) {
        super.setId("AB".concat(id));
    }

    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(String tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
    }

    public Boolean getEsImportado() {
        return esImportado;
    }

    public void setEsImportado(Boolean esImportado) {
        this.esImportado = esImportado;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }
}
