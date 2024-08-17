package main.java.com.market.modelo;

public final class ProductoLimpieza extends Producto {
    private TipoAplicacionLimpieza tipoAplicacion;

    public TipoAplicacionLimpieza getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(TipoAplicacionLimpieza tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }
}
