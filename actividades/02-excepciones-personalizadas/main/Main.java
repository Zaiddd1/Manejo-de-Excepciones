package main;

import banco.CuentaBancaria;
import excepciones.CuentaNoEncontradaException;
import excepciones.SaldoInsuficienteException;
import excepciones.SaldoNoCeroException;

public class Main {

    public static void main(String[] args) {

        System.out.println("== EXPERIENCIA 2: EXCEPCIONES PERSONALIZADAS ==");

        CuentaBancaria origen = new CuentaBancaria(2001, "Luis Ramos", 500.0);
        CuentaBancaria destino = new CuentaBancaria(2002, "Maria Lopez", 100.0);

        // Transferencia valida
        try {
            origen.transferir(destino, 200.0);
            System.out.println("Transferencia exitosa.");
            System.out.println("Origen: " + origen);
            System.out.println("Destino: " + destino);
        } catch (CuentaNoEncontradaException | SaldoInsuficienteException e) {
            System.out.println("Error en la transferencia: " + e.getMessage());
        }

        // Transferencia a una cuenta que no existe (se simula pasando null)
        try {
            origen.transferir(null, 50.0);
        } catch (CuentaNoEncontradaException | SaldoInsuficienteException e) {
            System.out.println("Error en la transferencia: " + e.getMessage());
        }

        // Transferencia con saldo insuficiente en la cuenta origen
        try {
            origen.transferir(destino, 10000.0);
        } catch (CuentaNoEncontradaException | SaldoInsuficienteException e) {
            System.out.println("Error en la transferencia: " + e.getMessage());
        }

        // Intento de cerrar una cuenta con saldo distinto de cero
        try {
            destino.cerrarCuenta();
        } catch (SaldoNoCeroException e) {
            System.out.println("Error al cerrar cuenta: " + e.getMessage());
        }

        // Se retira todo el saldo y se vuelve a intentar el cierre
        try {
            destino.retirar(destino.getSaldo());
            destino.cerrarCuenta();
            System.out.println("Cuenta cerrada correctamente: " + destino);
        } catch (SaldoInsuficienteException | SaldoNoCeroException e) {
            System.out.println("Error al cerrar cuenta: " + e.getMessage());
        }
    }
}
