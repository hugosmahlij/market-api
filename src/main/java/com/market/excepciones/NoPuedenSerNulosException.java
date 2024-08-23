package main.java.com.market.excepciones;

public class NoPuedenSerNulosException extends RuntimeException {
    public NoPuedenSerNulosException() {
        super("Producto o tienda no pueden ser nulos.");
    }
}
