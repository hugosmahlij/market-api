package main.java.com.market.excepciones;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException() {
        super("El valor ingresado no puede contener más de 3 caracteres.");
    }
}
