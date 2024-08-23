package main.java.com.market.excepciones;

public class GraduacionOCaloriasException extends RuntimeException {
    public GraduacionOCaloriasException() {
        super("La graduacion y las calorias no pueden ser nulas.");
    }
}
