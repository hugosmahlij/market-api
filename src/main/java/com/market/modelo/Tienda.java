package main.java.com.market.modelo;


import java.util.List;

public final class Tienda {
    private Integer id;
    private String nombre;
    private Double saldoCaja;


    public Integer getMaximoProductosStock(){
        return null; //TODO
    }

    public List<Producto> getProductosEnStock(){
        return null; //TODO
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(Double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }
}
