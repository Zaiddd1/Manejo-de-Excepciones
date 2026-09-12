package excepciones;

// Se lanza cuando se intenta cerrar una cuenta cuyo saldo todavia no es cero.
public class SaldoNoCeroException extends Exception {

    public SaldoNoCeroException(String mensaje) {
        super(mensaje);
    }
}
