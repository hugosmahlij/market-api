package main.java.com.market.modelo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class ProductoEnvasado extends Producto{
    private String tipoEnvase;
    private Boolean esImportado;
    private Date fechaVencimiento;
    private Integer calorias;

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
