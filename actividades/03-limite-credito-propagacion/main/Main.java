package main;

import banco.CuentaBancaria;
import banco.CuentaCredito;
import excepciones.LimiteCreditoExcedidoException;
import excepciones.SaldoInsuficienteException;

public class Main {

    public static void main(String[] args) {

        System.out.println("== EXPERIENCIA 3: LIMITE DE CREDITO Y PROPAGACION ==");

        CuentaCredito credito = new CuentaCredito(3001, "Rosa Diaz", 200.0, 300.0);
        CuentaBancaria destino = new CuentaBancaria(3002, "Pedro Sosa", 0.0);

        // Retiro dentro del saldo disponible
        procesarRetiro(credito, 150.0);
        System.out.println("Despues del primer retiro: " + credito);

        // Retiro que usa el limite de credito (el saldo queda negativo)
        procesarRetiro(credito, 100.0);
        System.out.println("Despues del segundo retiro (usa credito): " + credito);

        // Retiro que excede saldo + limite de credito: LimiteCreditoExcedidoException
        // se lanza dentro de retirar(), procesarRetiro() no la captura (es una
        // excepcion no verificada) y se propaga hasta este try/catch de main().
        try {
            procesarRetiro(credito, 1000.0);
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("Error propagado hasta main: " + e.getMessage());
        }

        // Una transferencia tambien pasa por retirar(), asi que puede propagar
        // la misma excepcion sin que transferir() la declare en su firma.
        try {
            credito.transferir(destino, 1000.0);
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("Error propagado hasta main (transferencia): " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Saldo insuficiente en la transferencia: " + e.getMessage());
        }
    }

    // Metodo intermedio: llama a retirar() pero no captura
    // LimiteCreditoExcedidoException. Al ser no verificada, el compilador no
    // lo exige, y por eso la excepcion se propaga tal cual hasta quien haya
    // invocado a procesarRetiro().
    private static void procesarRetiro(CuentaCredito cuenta, double monto) {
        try {
            cuenta.retirar(monto);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Saldo insuficiente: " + e.getMessage());
        }
    }
}
