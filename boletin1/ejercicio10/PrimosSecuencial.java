package boletin1.ejercicio10;

public class PrimosSecuencial {
    public static void main(String[] args) {
        int limite = 1000000;
        int contadorPrimos = 0;

        long tiempoInicio = System.currentTimeMillis();

        for (int i = 1; i <= limite; i++) {
            if (esPrimo(i)) {
                contadorPrimos++;
            }
        }

        long tiempoFin = System.currentTimeMillis();

        System.out.println("=== VERSIÓN SECUENCIAL ===");
        System.out.println("Número de primos encontrados: " + contadorPrimos);
        System.out.println("Tiempo empleado: " + (tiempoFin - tiempoInicio) + " ms");
    }

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
