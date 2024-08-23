package main.java.com.market.excepciones;

public class PrecioUnitarioInvalidoException extends RuntimeException {
    public PrecioUnitarioInvalidoException() {
        super("Precio unitario invalido.");
    }
}
