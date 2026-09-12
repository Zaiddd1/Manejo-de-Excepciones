// Demuestra la pregunta 9 del cuestionario: que pasa cuando, dentro de un
// try-with-resources, el bloque try lanza una excepcion Y el close() del
// recurso tambien lanza otra excepcion distinta.
//
// La excepcion del bloque try es la que se propaga y se captura en el catch;
// la excepcion lanzada por close() queda "suprimida" y se puede recuperar con
// getSuppressed().
public class PruebaExcepcionesSuprimidas {

    // Recurso de prueba cuyo uso y cierre lanzan excepciones distintas.
    static class RecursoProblematico implements AutoCloseable {

        void usar() {
            throw new RuntimeException("Error durante el uso del recurso");
        }

        @Override
        public void close() {
            throw new IllegalStateException("Error al cerrar el recurso");
        }
    }

    public static void main(String[] args) {

        try (RecursoProblematico recurso = new RecursoProblematico()) {

            recurso.usar();

        } catch (Exception e) {

            System.out.println("Excepcion capturada: " + e.getMessage());

            for (Throwable suprimida : e.getSuppressed()) {
                System.out.println("Excepcion suprimida: " + suprimida.getMessage());
            }
        }
    }
}
