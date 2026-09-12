package calculadora;

import excepciones.DivisionPorCeroException;

public class Main {

    public static void main(String[] args) {

        Calculadora calculadora = new Calculadora();

        try {

            System.out.println("Suma: "
                    + calculadora.sumar(10, 5));

            System.out.println("Resta: "
                    + calculadora.restar(10, 5));

            System.out.println("Multiplicacion: "
                    + calculadora.multiplicar(10, 5));

            // Division por cero: interrumpe el bloque try y salta al catch
            // de DivisionPorCeroException.
            System.out.println("Division: "
                    + calculadora.dividir(10, 0));

        } catch (DivisionPorCeroException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("Argumento invalido: "
                    + e.getMessage());

        } catch (ArithmeticException e) {

            System.out.println("Error aritmetico: "
                    + e.getMessage());
        }
    }
}
