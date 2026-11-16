package boletin1.ejercicioProyectoFinal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Subproceso trabajador que analiza una lista de archivos asignados.
 */
public class ProcesadorArchivo {

    public static void main(String[] args) {
        // Control de argumentos de entrada
        if (args.length < 1) {
            System.err.println("Error: No se han recibido archivos para procesar.");
            System.exit(1);
        }

        long numArchivos = 0;
        long numLineas = 0;
        long numPalabras = 0;
        long numCaracteres = 0;

        // Procesar cada archivo asignado a este trabajador
        for (String ruta : args) {
            File archivo = new File(ruta);

            // Manejar archivos inexistentes o directorios
            if (!archivo.exists() || !archivo.isFile()) {
                System.err.println("Advertencia: El archivo no existe o no es valido: " + ruta);
                continue;
            }

            try {
                // Manejo de lectura
                List<String> lineas = Files.readAllLines(archivo.toPath());
                numArchivos++;
                numLineas += lineas.size();

                for (String linea : lineas) {
                    numCaracteres += linea.length();
                    // Dividir la línea por espacios para contar palabras
                    String[] palabras = linea.trim().split("\\s+");
                    if (palabras.length > 0 && !palabras[0].isEmpty()) {
                        numPalabras += palabras.length;
                    }
                }
            } catch (IOException e) {
                // Manejo de errores de lectura por archivo
                System.err.println("Error de lectura en archivo: " + ruta + " -> " + e.getMessage());
            }
        }

        // Devolver las métricas agregadas al proceso padre mediante stdout
        System.out.println(numArchivos + ";" + numLineas + ";" + numPalabras + ";" + numCaracteres);
    }
}