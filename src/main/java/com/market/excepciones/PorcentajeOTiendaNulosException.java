package main.java.com.market.excepciones;

public class PorcentajeOTiendaNulosException extends RuntimeException {
    public PorcentajeOTiendaNulosException() {
        super("Porcentaje de descuento o tienda no pueden ser nulos.");
    }
}
