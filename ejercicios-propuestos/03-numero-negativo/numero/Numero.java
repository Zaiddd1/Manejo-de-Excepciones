package numero;

// Envuelve un valor double, validando que nunca sea negativo.
public class Numero {

    private double valor;

    public double getValor() {
        return valor;
    }

    // Asigna el valor solo si no es negativo; en caso contrario lanza
    // IllegalArgumentException (excepcion estandar de Java).
    public void setValor(double valor) {

        if (valor < 0) {
            throw new IllegalArgumentException(
                    "El valor no puede ser negativo"
            );
        }

        this.valor = valor;
    }
}
