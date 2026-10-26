package ejercicio8;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        int[] valores = {10, 20, 30, 40, 50};
        List<Process> procesos = new ArrayList<>();

        // Obtiene la ruta del ejecutable java activo
        String javaBin = ProcessHandle.current()
                .info()
                .command()
                .orElse("java");

        // Obtiene el classpath de las clases compiladas por IntelliJ
        String classPath = System.getProperty("java.class.path");

        try {
            for (int val : valores) {
                // Si la clase Calculador está en un paquete (ej: ejercicio8.Calculador), cámbialo aquí
                ProcessBuilder pb = new ProcessBuilder(
                        javaBin,
                        "-cp", classPath,
                        "Calculador", // Si usas paquete, pon "ejercicio8.Calculador"
                        String.valueOf(val)
                );

                pb.inheritIO();
                Process p = pb.start();
                procesos.add(p);
            }

            for (Process p : procesos) {
                p.waitFor();
            }

            System.out.println("Todos los procesos han terminado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}