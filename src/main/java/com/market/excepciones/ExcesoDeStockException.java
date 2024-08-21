package main.java.com.market.excepciones;

public class ExcesoDeStockException extends RuntimeException {
    public ExcesoDeStockException() {
        super("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock.");
    }
}
