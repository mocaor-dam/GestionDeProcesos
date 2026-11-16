package boletin1.ejercicio9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        // Pares de entrada requeridos
        int[][] datos = {
                {5, 3},
                {10, 2},
                {20, 4},
                {7, 8}
        };

        List<Process> procesos = new ArrayList<>();

        // 1. Obtener la ruta exacta del Java binario y el Classpath de IntelliJ
        String javaBin = ProcessHandle.current()
                .info()
                .command()
                .orElse("java");
        String classPath = System.getProperty("java.class.path");

        try {
            // 2. Lanzar los 4 procesos
            for (int[] par : datos) {
                ProcessBuilder pb = new ProcessBuilder(
                        javaBin,
                        "-cp", classPath,
                        "boletin1.ejercicio9.Calculadora",
                        String.valueOf(par[0]),
                        String.valueOf(par[1])
                );

                // IMPORTANTE: NO usamos inheritIO() porque queremos capturar la salida en el padre
                procesos.add(pb.start());
            }

            // 3. Leer los resultados de cada hijo por la salida estándar
            for (int i = 0; i < procesos.size(); i++) {
                Process p = procesos.get(i);

                // Leemos el canal de salida del hijo
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    String resultado = reader.readLine();
                    System.out.println("Resultado " + (i + 1) + ": " + resultado);
                }

                // Esperamos a que finalice el proceso
                p.waitFor();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
