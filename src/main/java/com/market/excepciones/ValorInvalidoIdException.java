package main.java.com.market.excepciones;

public class ValorInvalidoIdException extends RuntimeException {
    public ValorInvalidoIdException() {
        super("El valor ingresado no puede contener más de 3 caracteres.");
    }
}
