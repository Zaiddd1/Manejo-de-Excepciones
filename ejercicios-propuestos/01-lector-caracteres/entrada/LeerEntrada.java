package entrada;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

// Envuelve un InputStream (por ejemplo, System.in) para leer caracteres de
// a uno a la vez. Implementa AutoCloseable para poder usarse en un
// try-with-resources.
public class LeerEntrada implements AutoCloseable {

    private Reader stream;

    public LeerEntrada(InputStream fuente) {
        stream = new InputStreamReader(fuente);
    }

    // Lee y devuelve el siguiente caracter disponible en el flujo.
    public char getChar() throws IOException {
        return (char) this.stream.read();
    }

    // Cierra el flujo subyacente.
    @Override
    public void close() throws IOException {
        stream.close();
    }
}
