package banco;

import excepciones.SaldoInsuficienteException;

// Version base de esta experiencia: retirar() y transferir() se dejan como
// "ganchos" (hooks) que CuentaCredito puede redefinir con otra politica.
public class CuentaBancaria {

    protected int numeroCuenta;
    protected String titular;
    protected double saldo;

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

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        saldo += monto;
    }

    // Las subclases pueden sobrescribir este metodo para cambiar la politica
    // de retiro (por ejemplo, permitiendo sobregiro hasta un limite de credito).
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

    // Reutiliza retirar(), asi que cualquier politica de sobregiro definida en
    // una subclase tambien aplica automaticamente a las transferencias.
    public void transferir(CuentaBancaria destino, double monto) throws SaldoInsuficienteException {
        retirar(monto);
        destino.depositar(monto);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [numero=" + numeroCuenta + ", titular=" + titular
                + ", saldo=" + saldo + "]";
    }
}
