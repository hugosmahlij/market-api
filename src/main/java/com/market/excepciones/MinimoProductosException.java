package main.java.com.market.excepciones;

public class MinimoProductosException extends RuntimeException {
    public MinimoProductosException() {
        super("No se pueden vender más de 3 productos por venta.");
    }
}
