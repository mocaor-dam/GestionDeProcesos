package boletin1.ejercicio10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrimosPrincipal {
    public static void main(String[] args) {
        int limite = 1000000;
        int numProcesos = 4;
        int rango = limite / numProcesos;

        List<Process> procesos = new ArrayList<>();

        // Ruta de Java y del Classpath para ejecución compatible con IntelliJ
        String javaBin = ProcessHandle.current().info().command().orElse("java");
        String classPath = System.getProperty("java.class.path");

        long tiempoInicio = System.currentTimeMillis();

        try {
            // 1. Lanzar los 4 procesos en paralelo
            for (int i = 0; i < numProcesos; i++) {
                int inicio = i * rango + 1;
                int fin = (i == numProcesos - 1) ? limite : (i + 1) * rango;

                ProcessBuilder pb = new ProcessBuilder(
                        javaBin,
                        "-cp", classPath,
                        "TrabajadorPrimos", // Cambiar a "boletin1.ejercicio10.TrabajadorPrimos" si usas package
                        String.valueOf(inicio),
                        String.valueOf(fin)
                );

                procesos.add(pb.start());
            }

            // 2. Recoger los resultados de los subprocesos
            int totalPrimos = 0;
            for (Process p : procesos) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    String linea = reader.readLine();
                    if (linea != null) {
                        totalPrimos += Integer.parseInt(linea);
                    }
                }
                p.waitFor();
            }

            long tiempoFin = System.currentTimeMillis();

            // 3. Salida según ampliación
            System.out.println("=== VERSIÓN PARALELA ===");
            System.out.println("Número de primos encontrados: " + totalPrimos);
            System.out.println("Tiempo empleado: " + (tiempoFin - tiempoInicio) + " ms");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}