package excepciones;

// Se lanza cuando se intenta generar un reporte de una cuenta que todavia
// no registra ninguna transaccion.
public class HistorialVacioException extends Exception {

    public HistorialVacioException(String mensaje) {
        super(mensaje);
    }
}
