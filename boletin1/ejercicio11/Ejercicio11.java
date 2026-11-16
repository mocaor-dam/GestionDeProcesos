package boletin1.ejercicio11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ejercicio11 {
    public static void main(String[] args) {

        // =========================================================================
        // PARTE 1: Intentar ejecutar un comando que no existe y separar flujos
        // =========================================================================
        System.out.println("=== PARTE 1: SIN redirectErrorStream (Flujos separados) ===");
        try {
            // Ejecutamos una orden de consola que provocará un error (un archivo no encontrado)
            ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "/c", "dir", "archivo_inexistente.txt");
            Process p1 = pb1.start();

            // 1. Intentamos leer de stdout
            BufferedReader stdOut = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            String lineaStdout = stdOut.readLine();
            System.out.println("[STDOUT] " + (lineaStdout != null ? lineaStdout : "(vacío)"));

            // 2. Intentamos leer de stderr
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
            String lineaStderr;
            while ((lineaStderr = stdErr.readLine()) != null) {
                System.out.println("[STDERR] " + lineaStderr);
            }

            p1.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Excepción Java al lanzar el comando: " + e.getMessage());
        }

        System.out.println("\n-----------------------------------------------------------\n");

        // =========================================================================
        // PARTE 2: Utilizando redirectErrorStream(true)
        // =========================================================================
        System.out.println("=== PARTE 2: CON redirectErrorStream(true) (Flujos mezclados) ===");
        try {
            ProcessBuilder pb2 = new ProcessBuilder("cmd.exe", "/c", "dir", "archivo_inexistente.txt");

            // Unificamos la salida de error (stderr) dentro de la salida estándar (stdout)
            pb2.redirectErrorStream(true);

            Process p2 = pb2.start();

            // Leemos ÚNICAMENTE desde getInputStream()
            BufferedReader lectorUnificado = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String linea;
            while ((linea = lectorUnificado.readLine()) != null) {
                System.out.println("[FLUJO UNIFICADO] " + linea);
            }

            p2.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}