package main.java.com.market.excepciones;

public class MostrarStockTiendaException extends RuntimeException {
    public MostrarStockTiendaException() {
        super("La tienda no puede ser nula.");
    }
}
