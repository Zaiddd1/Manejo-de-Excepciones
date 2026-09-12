package excepciones;

// Se lanza cuando una cuenta no tiene saldo suficiente para un retiro
// o una transferencia.
public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
