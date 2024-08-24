package main.java.com.market.modelo;


import java.util.ArrayList;
import java.util.List;

public final class Tienda {
    private Integer id;
    private String nombre;
    private Double saldoCaja;
    private Integer maximoProductosStock;
    private List<Producto> listaProductos = new ArrayList<Producto>();

    public Tienda(List<Producto> listaProductos, Double saldoCaja, int maximoProductosStock) {
        this.listaProductos = listaProductos != null ? new ArrayList<>(listaProductos) : new ArrayList<>();
        this.saldoCaja = saldoCaja;
        this.maximoProductosStock = maximoProductosStock;
    }

    public Integer stockTotalProductos() {
        Integer sumaStock = 0;
        for (int i = 0; i < listaProductos.size(); i++) {
            sumaStock += listaProductos.get(i).getStock() == null ? 0 : listaProductos.get(i).getStock();
        }
        return sumaStock;
    }

    public List<Producto> getProductosEnStock() {
        List<Producto> productosEnStock = new ArrayList<>();
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto producto = listaProductos.get(i);
            if (producto.getStock() > 0 && producto.getEstaDisponibleVenta()) {
                productosEnStock.add(producto);
            }
        }
        return productosEnStock;
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

    public Integer getMaximoProductosStock() {
        return maximoProductosStock;
    }

    public void setMaximoProductosStock(Integer maximoProductosStock) {
        this.maximoProductosStock = maximoProductosStock;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = new ArrayList<>(listaProductos);
    }
}
