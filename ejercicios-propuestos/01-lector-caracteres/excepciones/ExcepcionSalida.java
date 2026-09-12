package excepciones;

// Se lanza cuando el caracter leido es el caracter de salida ('x' o 'X').
public class ExcepcionSalida extends Exception {

    public ExcepcionSalida(String mensaje) {
        super(mensaje);
    }
}
