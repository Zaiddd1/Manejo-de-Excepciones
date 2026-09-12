package excepciones;

// Se lanza cuando un retiro o transferencia supera el limite de credito
// disponible de una CuentaCredito. Se extiende IllegalArgumentException (no
// verificada) siguiendo el mismo criterio que InsufficientStockException en
// la guia teorica: representa un argumento invalido para la operacion y, al
// ser no verificada, puede lanzarse desde retirar() sin cambiar su firma y se
// propaga automaticamente por la pila de llamadas hasta que alguien la capture.
public class LimiteCreditoExcedidoException extends IllegalArgumentException {

    public LimiteCreditoExcedidoException(String mensaje) {
        super(mensaje);
    }
}
