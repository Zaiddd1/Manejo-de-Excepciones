package banco;

import excepciones.CuentaNoEncontradaException;
import excepciones.SaldoInsuficienteException;
import excepciones.SaldoNoCeroException;

// Amplia la cuenta de la Experiencia 1 con transferencias entre cuentas y
// cierre de cuenta, usando las nuevas excepciones personalizadas.
public class CuentaBancaria {

    private int numeroCuenta;
    private String titular;
    private double saldo;
    private boolean cerrada;

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
    public boolean isCerrada() { return cerrada; }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        saldo += monto;
    }

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

    // Transfiere dinero a otra cuenta. Si la cuenta destino no existe (se
    // recibe null) se lanza CuentaNoEncontradaException; si no hay saldo
    // suficiente, se propaga la SaldoInsuficienteException de retirar().
    public void transferir(CuentaBancaria destino, double monto)
            throws CuentaNoEncontradaException, SaldoInsuficienteException {

        if (destino == null) {
            throw new CuentaNoEncontradaException("La cuenta destino no existe.");
        }
        retirar(monto);
        destino.depositar(monto);
    }

    // Cierra la cuenta solo si el saldo esta exactamente en cero.
    public void cerrarCuenta() throws SaldoNoCeroException {
        if (saldo != 0) {
            throw new SaldoNoCeroException(
                    "No se puede cerrar la cuenta: el saldo es " + saldo + ", debe ser cero.");
        }
        cerrada = true;
    }

    @Override
    public String toString() {
        return "CuentaBancaria [numero=" + numeroCuenta + ", titular=" + titular
                + ", saldo=" + saldo + ", cerrada=" + cerrada + "]";
    }
}
