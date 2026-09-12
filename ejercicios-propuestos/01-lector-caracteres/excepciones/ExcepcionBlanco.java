package excepciones;

// Se lanza cuando el caracter leido es un espacio en blanco.
public class ExcepcionBlanco extends Exception {

    public ExcepcionBlanco(String mensaje) {
        super(mensaje);
    }
}
