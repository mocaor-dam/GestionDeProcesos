package boletin1.ejercicio12;

/**
 * Clase Trabajador que actúa como subproceso hijo.
 * Calcula la cantidad de números pares, impares y la suma total dentro de un intervalo.
 */
public class Trabajador {

    public static void main(String[] args) {
        // Control de argumentos de entrada
        if (args.length < 2) {
            System.err.println("Error: Se requieren dos argumentos (inicio y fin).");
            System.exit(1);
        }

        try {
            long inicio = Long.parseLong(args[0]);
            long fin = Long.parseLong(args[1]);

            long pares = 0;
            long impares = 0;
            long suma = 0;

            // Recorrido del intervalo asignado
            for (long i = inicio; i <= fin; i++) {
                suma += i;
                if (i % 2 == 0) {
                    pares++;
                } else {
                    impares++;
                }
            }

            // Envío de resultados al proceso padre a través de la salida estándar (stdout)
            System.out.println("Pares: " + pares);
            System.out.println("Impares: " + impares);
            System.out.println("Suma: " + suma);

        } catch (NumberFormatException e) {
            System.err.println("Error: Argumentos no válidos en el trabajador.");
            System.exit(2);
        }
    }
}