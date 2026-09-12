package excepciones;

// Se lanza cuando el caracter leido es un digito.
public class ExcepcionNumero extends Exception {

    public ExcepcionNumero(String mensaje) {
        super(mensaje);
    }
}
