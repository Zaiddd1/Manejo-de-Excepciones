package banco;

import excepciones.SaldoInsuficienteException;

// Representa una cuenta bancaria simple: numero, titular y saldo, con
// validaciones basicas usando excepciones estandar y una personalizada.
public class CuentaBancaria {

    private int numeroCuenta;
    private String titular;
    private double saldo;

    // Valida que el saldo inicial no sea negativo.
    public CuentaBancaria(int numeroCuenta, String titular, double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }

    // Aumenta el saldo; el monto debe ser positivo.
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        saldo += monto;
    }

    // Descuenta el saldo; el monto debe ser positivo y no superar el saldo disponible.
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente: disponible " + saldo + ", solicitado " + monto);
        }
        saldo -= monto;
    }

    @Override
    public String toString() {
        return "CuentaBancaria [numero=" + numeroCuenta + ", titular=" + titular
                + ", saldo=" + saldo + "]";
    }
}
