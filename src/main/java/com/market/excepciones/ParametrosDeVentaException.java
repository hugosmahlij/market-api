package main.java.com.market.excepciones;

public class ParametrosDeVentaException extends RuntimeException {
    public ParametrosDeVentaException() {
        super("Los parámetros de la venta no pueden ser nulos.");
    }
}
