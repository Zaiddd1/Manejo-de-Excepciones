package principal;

import java.io.IOException;

import entrada.LeerEntrada;
import excepciones.ExcepcionBlanco;
import excepciones.ExcepcionNumero;
import excepciones.ExcepcionSalida;
import excepciones.ExcepcionVocal;

public class Main {

    public static void main(String[] args) {

        // try-with-resources: LeerEntrada se cierra automaticamente al salir
        // del bloque, incluso si el bucle termina por una excepcion.
        try (LeerEntrada entrada = new LeerEntrada(System.in)) {

            ProcesadorCaracteres procesador =
                    new ProcesadorCaracteres(entrada);

            System.out.println("Ingrese caracteres.");
            System.out.println("Presione X para salir.");

            while (true) {

                try {

                    procesador.procesar();

                } catch (ExcepcionVocal e) {

                    // Las excepciones de vocal, numero y blanco solo informan
                    // el motivo y permiten seguir leyendo caracteres.
                    System.out.println("Excepcion de vocal: "
                            + e.getMessage());

                } catch (ExcepcionNumero e) {

                    System.out.println("Excepcion de numero: "
                            + e.getMessage());

                } catch (ExcepcionBlanco e) {

                    System.out.println("Excepcion de blanco: "
                            + e.getMessage());

                } catch (ExcepcionSalida e) {

                    // La excepcion de salida termina el bucle de lectura.
                    System.out.println("Excepcion de salida: "
                            + e.getMessage());

                    break;
                }
            }

        } catch (IOException e) {

            System.out.println("Error de entrada/salida: "
                    + e.getMessage());
        }
    }
}
