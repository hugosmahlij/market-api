package main.java.com.market.excepciones;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
    }
}
