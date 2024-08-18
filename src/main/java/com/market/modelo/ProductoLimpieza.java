package main.java.com.market.modelo;

import org.jetbrains.annotations.NotNull;

public final class ProductoLimpieza extends Producto {
    private TipoAplicacionLimpieza tipoAplicacion;


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
