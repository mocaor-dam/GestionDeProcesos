package boletin1.ejercicioProyectoFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Proceso principal que distribuye archivos entre subprocesos y consolida el resumen.
 */
public class Principal {

    public static void main(String[] args) {
        long tiempoInicio = System.currentTimeMillis();

        // 1. Manejo de argumentos incorrectos
        if (args.length < 1) {
            System.err.println("Error: Debes indicar la ruta de la carpeta con archivos.");
            System.err.println("Uso: java Principal <ruta_carpeta>");
            System.exit(1);
        }

        // 2. Manejo de carpetas o archivos inexistentes
        File carpeta = new File(args[0]);
        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.err.println("Error: La ruta especificada no existe o no es un directorio valido.");
            System.exit(2);
        }

        // Obtener la lista de archivos válidos
        File[] listaFicheros = carpeta.listFiles(File::isFile);
        if (listaFicheros == null || listaFicheros.length == 0) {
            System.out.println("No se encontraron archivos para procesar en la carpeta.");
            return;
        }

        int numProcesos = 3;
        List<List<String>> lotes = new ArrayList<>();
        for (int i = 0; i < numProcesos; i++) {
            lotes.add(new ArrayList<>());
        }

        // Reparto equitativo (Round-Robin) de archivos entre los 3 procesos
        for (int i = 0; i < listaFicheros.length; i++) {
            lotes.get(i % numProcesos).add(listaFicheros[i].getAbsolutePath());
        }

        String javaBin = ProcessHandle.current().info().command().orElse("java");
        String classPath = System.getProperty("java.class.path");
        String claseTrabajador = ProcesadorArchivo.class.getName();

        List<Process> procesos = new ArrayList<>();

        try {
            // 3. Crear y lanzar subprocesos
            for (List<String> lote : lotes) {
                if (lote.isEmpty()) continue; // Si hay menos de 3 archivos

                List<String> comando = new ArrayList<>();
                comando.add(javaBin);
                comando.add("-cp");
                comando.add(classPath);
                comando.add(claseTrabajador);
                comando.addAll(lote);

                ProcessBuilder pb = new ProcessBuilder(comando);
                procesos.add(pb.start());
            }

            long totalArchivos = 0;
            long totalLineas = 0;
            long totalPalabras = 0;
            long totalCaracteres = 0;

            // 4. Recoger resultados y controlar códigos de salida
            for (int i = 0; i < procesos.size(); i++) {
                Process p = procesos.get(i);

                // Leer métricas enviadas por stdout desde el hijo
                try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    String respuesta = br.readLine();
                    if (respuesta != null && !respuesta.isEmpty()) {
                        String[] datos = respuesta.split(";");
                        totalArchivos += Long.parseLong(datos[0]);
                        totalLineas += Long.parseLong(datos[1]);
                        totalPalabras += Long.parseLong(datos[2]);
                        totalCaracteres += Long.parseLong(datos[3]);
                    }
                }

                // Esperar al proceso y controlar códigos de salida / interrupciones
                int exitCode = p.waitFor();
                if (exitCode != 0) {
                    System.err.println("Advertencia: El subproceso " + (i + 1) + " finalizo con codigo de error: " + exitCode);
                }
            }

            long tiempoTotal = System.currentTimeMillis() - tiempoInicio;

            // 5. Imprimir informe final según la salida requerida
            System.out.println("========== RESUMEN ==========");
            System.out.println();
            System.out.println("Archivos procesados: " + totalArchivos);
            System.out.println("Lineas:              " + totalLineas);
            System.out.println("Palabras:            " + totalPalabras);
            System.out.println("Caracteres:          " + totalCaracteres);
            System.out.println();
            System.out.println("Procesos utilizados: " + procesos.size());
            System.out.println("Tiempo total:        " + tiempoTotal + " ms");
            System.out.println("==============================");

        } catch (IOException e) {
            System.err.println("Error de entrada/salida al ejecutar los subprocesos: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El proceso principal fue interrumpido inesperadamente.");
            Thread.currentThread().interrupt();
        }
    }
}