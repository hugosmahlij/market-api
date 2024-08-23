package main.java.com.market.excepciones;

public class ValorInvalidoPrecioFinalException extends RuntimeException {
    public ValorInvalidoPrecioFinalException() {
        super("Precio unitario o porcentaje de ganancia no pueden estar vacios.");
    }
}
