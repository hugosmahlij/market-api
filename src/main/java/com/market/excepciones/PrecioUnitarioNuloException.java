package main.java.com.market.excepciones;

public class PrecioUnitarioNuloException extends RuntimeException {
  public PrecioUnitarioNuloException() {
    super("El precio unitario no puede ser nulo");
  }
}
