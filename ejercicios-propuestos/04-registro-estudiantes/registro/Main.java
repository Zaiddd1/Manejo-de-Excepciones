package registro;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {

        RegistroEstudiantes registro =
                new RegistroEstudiantes(5);

        try {

            registro.agregarEstudiante("Carlos");
            registro.agregarEstudiante("Ana");
            registro.agregarEstudiante("Luis");

            System.out.println("Estudiantes agregados correctamente.");

            System.out.println("Estudiante encontrado: "
                    + registro.buscarEstudiante("Ana"));

            // "Pedro" no fue agregado: buscarEstudiante() lanza
            // NoSuchElementException y el flujo salta al catch de abajo.
            System.out.println("Estudiante encontrado: "
                    + registro.buscarEstudiante("Pedro"));

        } catch (IllegalArgumentException e) {

            System.out.println("Error al agregar estudiante: "
                    + e.getMessage());

        } catch (NoSuchElementException e) {

            System.out.println("Error al buscar estudiante: "
                    + e.getMessage());
        }
    }
}
