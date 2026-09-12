package excepciones;

// Se lanza cuando se intenta dividir por cero en la Calculadora.
public class DivisionPorCeroException extends Exception {

    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}
