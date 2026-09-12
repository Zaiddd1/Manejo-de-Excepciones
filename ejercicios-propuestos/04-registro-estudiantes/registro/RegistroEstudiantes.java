package registro;

import java.util.NoSuchElementException;

// Guarda nombres de estudiantes en un arreglo de tamano fijo y permite
// buscarlos por nombre.
public class RegistroEstudiantes {

    private String[] estudiantes;
    private int cantidad;

    public RegistroEstudiantes(int capacidad) {
        estudiantes = new String[capacidad];
        cantidad = 0;
    }

    // Agrega un estudiante; valida que el nombre no sea nulo/vacio y que
    // todavia haya espacio disponible en el arreglo.
    public void agregarEstudiante(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede ser nulo o vacio"
            );
        }

        if (cantidad >= estudiantes.length) {
            throw new IllegalArgumentException(
                    "El arreglo de estudiantes esta lleno"
            );
        }

        estudiantes[cantidad] = nombre;
        cantidad++;
    }

    // Busca un estudiante por nombre (sin distinguir mayusculas/minusculas).
    // Si no lo encuentra, lanza NoSuchElementException (excepcion estandar
    // de java.util).
    public String buscarEstudiante(String nombre) {

        for (int i = 0; i < cantidad; i++) {

            if (estudiantes[i].equalsIgnoreCase(nombre)) {
                return estudiantes[i];
            }
        }

        throw new NoSuchElementException(
                "El estudiante no se encuentra en el registro"
        );
    }
}
