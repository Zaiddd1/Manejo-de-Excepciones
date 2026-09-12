package banco;

import java.util.ArrayList;
import java.util.List;

import excepciones.SaldoInsuficienteException;

// Ademas de saldo y titular, esta version registra un historial de
// transacciones en memoria, que luego usara ReporteTransacciones.
public class CuentaBancaria {

    private int numeroCuenta;
    private String titular;
    private double saldo;
    private List<String> historial = new ArrayList<>();

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
    public List<String> getHistorial() { return historial; }

    // Aumenta el saldo y registra el movimiento en el historial.
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        saldo += monto;
        historial.add("Deposito de " + monto + ". Saldo resultante: " + saldo);
    }

    // Descuenta el saldo y registra el movimiento en el historial.
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente: disponible " + saldo + ", solicitado " + monto);
        }
        saldo -= monto;
        historial.add("Retiro de " + monto + ". Saldo resultante: " + saldo);
    }

    @Override
    public String toString() {
        return "CuentaBancaria [numero=" + numeroCuenta + ", titular=" + titular + ", saldo=" + saldo + "]";
    }
}
