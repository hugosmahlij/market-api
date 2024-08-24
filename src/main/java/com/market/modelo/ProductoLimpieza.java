package main.java.com.market.modelo;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public final class ProductoLimpieza extends Producto {
    private TipoAplicacionLimpieza tipoAplicacion;

    public ProductoLimpieza(String id, String descripcion, BigDecimal precioUnitario, Integer stock, TipoAplicacionLimpieza tipoAplicacion, BigDecimal porcentajeDescuento) {
        super(id, descripcion, precioUnitario, stock, porcentajeDescuento);
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    public void setId(@NotNull String id) {
        super.setId("AZ".concat(id));
    }

    public TipoAplicacionLimpieza getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(TipoAplicacionLimpieza tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }
}
