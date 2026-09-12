package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import banco.CuentaBancaria;
import excepciones.HistorialVacioException;
import excepciones.SaldoInsuficienteException;
import reportes.ReporteTransacciones;

public class Main {

    public static void main(String[] args) throws SaldoInsuficienteException {

        System.out.println("== EXPERIENCIA 4: REPORTES DE TRANSACCIONES Y MANEJO DE RECURSOS ==");

        ReporteTransacciones reporte = new ReporteTransacciones();

        CuentaBancaria cuentaSinMovimientos = new CuentaBancaria(4001, "Jorge Vera", 0.0);

        CuentaBancaria cuentaConMovimientos = new CuentaBancaria(4002, "Elena Ruiz", 100.0);
        cuentaConMovimientos.depositar(50.0);
        cuentaConMovimientos.retirar(30.0);

        // Intentar generar el reporte de una cuenta sin transacciones
        try {
            reporte.generarReporte(cuentaSinMovimientos, "reporte-4001.txt");
        } catch (HistorialVacioException e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

        // Generar el reporte de una cuenta con movimientos
        try {
            reporte.generarReporte(cuentaConMovimientos, "reporte-4002.txt");
            System.out.println("Reporte generado en reporte-4002.txt");
        } catch (HistorialVacioException e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

        // Leer el reporte generado
        System.out.println("Contenido del reporte:");
        try {
            reporte.leerReporte("reporte-4002.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }

        // Intentar leer un archivo que no existe
        try {
            reporte.leerReporte("reporte-inexistente.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }
}
