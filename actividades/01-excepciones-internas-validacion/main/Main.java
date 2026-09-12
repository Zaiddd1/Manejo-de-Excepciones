package main;

import banco.CuentaBancaria;
import excepciones.SaldoInsuficienteException;

public class Main {

    public static void main(String[] args) {

        System.out.println("== EXPERIENCIA 1: EXCEPCIONES INTERNAS Y VALIDACION DE DATOS ==");

        // Creacion de una cuenta con datos validos
        CuentaBancaria cuenta = new CuentaBancaria(1001, "Carlos Perez", 500.0);
        System.out.println("Cuenta creada: " + cuenta);

        // Intento de crear una cuenta con saldo inicial negativo
        try {
            new CuentaBancaria(1002, "Ana Torres", -100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear cuenta: " + e.getMessage());
        }

        // Deposito valido
        cuenta.depositar(200.0);
        System.out.println("Despues de depositar 200: " + cuenta);

        // Deposito invalido: monto no positivo
        try {
            cuenta.depositar(-50.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al depositar: " + e.getMessage());
        }

        // Retiro valido
        try {
            cuenta.retirar(300.0);
            System.out.println("Despues de retirar 300: " + cuenta);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error al retirar: " + e.getMessage());
        }

        // Retiro invalido: excede el saldo disponible
        try {
            cuenta.retirar(1000.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error al retirar: " + e.getMessage());
        }

        // Retiro invalido: monto no positivo
        try {
            cuenta.retirar(-20.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error al retirar: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al retirar: " + e.getMessage());
        }
    }
}
