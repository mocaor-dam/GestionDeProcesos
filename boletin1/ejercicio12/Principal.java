package boletin1.ejercicio12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Principal que coordina y gestiona la ejecución en paralelo de 4 trabajadores.
 */
public class Principal {

    public static void main(String[] args) {
        // 8. Controlar posibles errores de entrada
        if (args.length < 1) {
            System.err.println("Uso: java Principal <numero_limite>");
            System.err.println("Ejemplo: java Principal 1000000");
            System.exit(1);
        }

        long limite = 0;
        try {
            limite = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Error: El límite debe ser un número entero válido.");
            System.exit(1);
        }

        int numTrabajadores = 4;
        long rango = limite / numTrabajadores;

        List<Process> procesos = new ArrayList<>();
        long tiempoInicio = System.currentTimeMillis();

        System.out.println("=== PROCESAMIENTO PARALELO ===\n");

        // Rutas del ejecutable java y classpath para compatibilidad (IntelliJ / Terminal)
        String javaBin = ProcessHandle.current().info().command().orElse("java");
        String classPath = System.getProperty("java.class.path");

        try {
            // 1. Crear y 2. Lanzar los procesos
            for (int i = 0; i < numTrabajadores; i++) {
                long inicio = i * rango + 1;
                // Si la división no es exacta, el último trabajador asume el resto hasta el límite
                long fin = (i == numTrabajadores - 1) ? limite : (i + 1) * rango;

                ProcessBuilder pb = new ProcessBuilder(
                        javaBin,
                        "-cp", classPath,
                        "boletin1.ejercicio12.Trabajador",
                        String.valueOf(inicio),
                        String.valueOf(fin)
                );

                procesos.add(pb.start());
            }

            // Variables para 5. Combinar los resultados globales
            long totalPares = 0;
            long totalImpares = 0;
            long sumaTotal = 0;

            // 3. Recoger sus resultados y 4. Esperar a que terminen
            for (int i = 0; i < procesos.size(); i++) {
                Process p = procesos.get(i);

                // Lectura de la salida estándar del subproceso
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        if (linea.startsWith("Pares: ")) {
                            totalPares += Long.parseLong(linea.replace("Pares: ", "").trim());
                        } else if (linea.startsWith("Impares: ")) {
                            totalImpares += Long.parseLong(linea.replace("Impares: ", "").trim());
                        } else if (linea.startsWith("Suma: ")) {
                            sumaTotal += Long.parseLong(linea.replace("Suma: ", "").trim());
                        }
                    }
                }

                // Esperar finalización del subproceso y comprobar código de salida
                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    System.out.println("Trabajador " + (i + 1) + " finalizado");
                } else {
                    System.err.println("Trabajador " + (i + 1) + " finalizó con errores (Código: " + exitCode + ")");
                }
            }

            // 7. Cálculo del tiempo total de ejecución
            long tiempoTotal = System.currentTimeMillis() - tiempoInicio;

            // 6. Mostrar los resultados globales según la salida esperada
            System.out.println("\nRESULTADO GLOBAL");
            System.out.println("----------------");
            System.out.println("Pares: " + totalPares);
            System.out.println("Impares: " + totalImpares);
            System.out.println("Suma total: " + sumaTotal);
            System.out.println("\nTiempo: " + tiempoTotal + " ms");

        } catch (IOException e) {
            System.err.println("Error E/S durante la ejecución paralela: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El proceso principal fue interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
