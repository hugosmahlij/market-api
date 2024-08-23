package main.java.com.market.excepciones;

public class MaximoUnidadesException extends RuntimeException {
    public MaximoUnidadesException() {
        super("No se pueden vender más de 12 unidades por producto.");
    }
}
