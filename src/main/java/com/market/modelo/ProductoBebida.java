package main.java.com.market.modelo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class ProductoBebida extends Producto{
    private Double graduacionAlcohol;
    private Boolean esImportado;
    private Date fechaVencimiento;
    private Integer calorias;

    @Override
    public void setId(@NotNull String id) {
        super.setId("AC".concat(id));
    }

    public Double getGraduacionAlcohol() {
        return graduacionAlcohol;
    }

    public void setGraduacionAlcohol(Double graduacionAlcohol) {
        this.graduacionAlcohol = graduacionAlcohol;
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
