package calculadora;

import excepciones.DivisionPorCeroException;

// Operaciones matematicas basicas. Solo dividir() puede fallar, por lo que
// es el unico metodo que declara una excepcion.
public class Calculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    // Divide a entre b; lanza DivisionPorCeroException si b es cero.
    public double dividir(double a, double b)
            throws DivisionPorCeroException {

        if (b == 0) {
            throw new DivisionPorCeroException(
                    "No se puede dividir por cero"
            );
        }

        return a / b;
    }
}
