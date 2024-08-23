package main.java.com.market.excepciones;

public class ProductoNuloExcepetion extends RuntimeException {
    public ProductoNuloExcepetion() {
        super("El producto no puede ser nulo.");
    }
}
