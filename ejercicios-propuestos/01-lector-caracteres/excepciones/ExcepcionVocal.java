package excepciones;

// Se lanza cuando el caracter leido es una vocal.
public class ExcepcionVocal extends Exception {

    public ExcepcionVocal(String mensaje) {
        super(mensaje);
    }
}
