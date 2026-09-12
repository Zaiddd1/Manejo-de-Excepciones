package excepciones;

// Se lanza cuando se hace referencia a una cuenta que no existe en el sistema
// (por ejemplo, la cuenta destino de una transferencia).
public class CuentaNoEncontradaException extends Exception {

    public CuentaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
