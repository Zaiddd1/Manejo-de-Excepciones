package banco;

import excepciones.LimiteCreditoExcedidoException;
import excepciones.SaldoInsuficienteException;

// Cuenta que permite sobregirarse hasta un limite de credito adicional.
public class CuentaCredito extends CuentaBancaria {

    private double limiteCredito;

    public CuentaCredito(int numeroCuenta, String titular, double saldo, double limiteCredito) {
        super(numeroCuenta, titular, saldo);
        this.limiteCredito = limiteCredito;
    }

    public double getLimiteCredito() { return limiteCredito; }

    // Redefine el retiro: ahora se permite retirar mas del saldo, siempre que
    // no se supere saldo + limiteCredito. Si se supera, se lanza
    // LimiteCreditoExcedidoException (no verificada) en lugar de
    // SaldoInsuficienteException.
    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        double disponible = saldo + limiteCredito;
        if (monto > disponible) {
            throw new LimiteCreditoExcedidoException(
                    "Se supero el limite de credito: disponible " + disponible
                            + " (saldo " + saldo + " + limite " + limiteCredito
                            + "), solicitado " + monto);
        }
        saldo -= monto;
    }
}
