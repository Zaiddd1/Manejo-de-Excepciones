package reportes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import banco.CuentaBancaria;
import excepciones.HistorialVacioException;

// Genera y lee reportes de transacciones de una cuenta en un archivo de texto.
public class ReporteTransacciones {

    // Escribe numeroCuenta, titular, saldo y el historial de la cuenta en un
    // archivo. Usa try-with-resources para garantizar que el PrintWriter se
    // cierre incluso si ocurre una excepcion durante la escritura.
    public void generarReporte(CuentaBancaria cuenta, String rutaArchivo)
            throws HistorialVacioException, IOException {

        if (cuenta.getHistorial().isEmpty()) {
            throw new HistorialVacioException(
                    "La cuenta " + cuenta.getNumeroCuenta() + " no tiene transacciones registradas.");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("numeroCuenta=" + cuenta.getNumeroCuenta());
            writer.println("titular=" + cuenta.getTitular());
            writer.println("saldo=" + cuenta.getSaldo());
            for (String movimiento : cuenta.getHistorial()) {
                writer.println("movimiento=" + movimiento);
            }
        }
    }

    // Lee un reporte previamente generado. Usa try-with-resources para
    // garantizar que el Scanner se cierre incluso si ocurre una excepcion
    // durante la lectura.
    public void leerReporte(String rutaArchivo) throws FileNotFoundException {
        try (Scanner lector = new Scanner(new File(rutaArchivo))) {
            while (lector.hasNextLine()) {
                System.out.println("  " + lector.nextLine());
            }
        }
    }
}
