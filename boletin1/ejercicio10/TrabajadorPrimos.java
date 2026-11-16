package boletin1.ejercicio10;

public class TrabajadorPrimos {
    public static void main(String[] args) {
        if (args.length < 2) return;

        int inicio = Integer.parseInt(args[0]);
        int fin = Integer.parseInt(args[1]);

        int contadorPrimos = 0;
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                contadorPrimos++;
            }
        }

        // Se envía el resultado total del rango al proceso padre por stdout
        System.out.println(contadorPrimos);
    }

    // Algoritmo de verificación de número primo
    private static boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
