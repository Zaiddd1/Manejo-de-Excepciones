package principal;

import java.io.IOException;

import entrada.LeerEntrada;
import excepciones.ExcepcionBlanco;
import excepciones.ExcepcionNumero;
import excepciones.ExcepcionSalida;
import excepciones.ExcepcionVocal;

// Lee un caracter usando LeerEntrada y, segun su valor, lanza la excepcion
// personalizada que corresponda.
public class ProcesadorCaracteres {

    private LeerEntrada entrada;
    private char caracter;

    public ProcesadorCaracteres(LeerEntrada entrada) {
        this.entrada = entrada;
    }

    // Procesa un caracter leido desde teclado y lanza la excepcion adecuada
    // segun el tipo de caracter (salida, vocal, numero o blanco).
    public void procesar()
            throws IOException, ExcepcionVocal, ExcepcionNumero,
            ExcepcionBlanco, ExcepcionSalida {

        caracter = entrada.getChar();

        // El caracter de salida se comprueba primero para poder terminar el
        // programa antes de evaluar cualquier otra condicion.
        if (caracter == 'x' || caracter == 'X') {
            throw new ExcepcionSalida(
                    "Se ingreso el caracter de salida."
            );
        }

        if (Character.isLetter(caracter)) {

            // Solo las vocales generan excepcion; las demas letras se
            // consideran caracteres validos.
            if ("aeiouAEIOU".indexOf(caracter) >= 0) {
                throw new ExcepcionVocal(
                        "Se ingreso una vocal: " + caracter
                );
            }
        }

        if (Character.isDigit(caracter)) {
            throw new ExcepcionNumero(
                    "Se ingreso un numero: " + caracter
            );
        }

        if (Character.isWhitespace(caracter)) {
            throw new ExcepcionBlanco(
                    "Se ingreso un espacio en blanco."
            );
        }

        // Si no coincide con ninguna condicion anterior, es un caracter valido.
        System.out.println("Caracter ingresado: " + caracter);
    }
}
