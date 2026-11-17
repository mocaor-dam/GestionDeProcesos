package boletin2;

// 3. Clase Principal
public class Ejercicio1_2 {
    public static void main(String[] args) {
        HiloThread h1 = new HiloThread("Hilo-Thread");
        Thread h2 = new Thread(new HiloRunnable(), "Hilo-Runnable");

        // Siempre se usa start(), nunca run() directamente
        h1.start();
        h2.start();
    }
}