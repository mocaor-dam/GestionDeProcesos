package tarea_para_practicar;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class CarreraHilos {
    private static final int TOTAL_TRAMOS = 5;

    private static final AtomicBoolean hayGanador = new AtomicBoolean(false);

    static void main(String[] args) {
        System.out.println("=== INICIO DE LA CARRERA (" + TOTAL_TRAMOS + " TRAMOS) ===\n");

        String[] nombreCorredores = {"Hilo-Relámpago", "Hilo-Sónico", "Hilo-Tortuga", "Hilo-Fénix"};
        Thread[] hilos = new Thread[nombreCorredores.length];

        for (int i = 0; i < nombreCorredores.length; i++) {
            Corredor corredor = new Corredor(nombreCorredores[i], TOTAL_TRAMOS);
            hilos[i] = new Thread(corredor, nombreCorredores[i]);
        }

        for (Thread hilo : hilos){
            hilo.start();
        }

        for (Thread hilo : hilos){
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("La carrera fue interrumpida inesperadamente.");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\n=== TODOS LOS CORREDORES HAN LLEGADO A LA META ===");

    }
    /**
     * Clase interna que representa a cada corredor.
     * Implementa Runnable para permitir su ejecución en un hilo independiente.
     */
    static class Corredor implements Runnable {
        private final String nombre;
        private final int totalTramos;
        private final Random random;

        /**
         * Constructor del corredor.
         * @param nombre Identificador del corredor/hilo.
         * @param totalTramos Cantidad de etapas de la carrera.
         */
        public Corredor(String nombre, int totalTramos) {
            this.nombre = nombre;
            this.totalTramos = totalTramos;
            this.random = new Random();
        }

        @Override
        public void run() {
            try {
                // Bucle que simula el avance tramo por tramo
                for (int tramo = 1; tramo <= totalTramos; tramo++) {
                    // Simula el esfuerzo/tiempo variable en recorrer el tramo (entre 300ms y 1000ms)
                    int tiempoTramo = 300 + random.nextInt(701);
                    Thread.sleep(tiempoTramo);

                    // Muestra el progreso actual por consola
                    System.out.printf("[%s] ha completado el tramo %d de %d (tiempo: %d ms)%n",
                            nombre, tramo, totalTramos, tiempoTramo);
                }

                // Notifica que el hilo ha finalizado su recorrido completo
                System.out.println(">>> [" + nombre + "] HA TERMINADO LA CARRERA <<<");

                // Comprobación atómica: si 'hayGanador' es false, lo cambia a true y entra al bloque
                if (hayGanador.compareAndSet(false, true)) {
                    System.out.println("\n**************************************************");
                    System.out.println("  ¡EL HILO GANADOR ES: " + nombre.toUpperCase() + "!");
                    System.out.println("**************************************************\n");
                }

            } catch (InterruptedException e) {
                System.err.println("El corredor " + nombre + " sufrió una lesión (hilo interrumpido).");
                Thread.currentThread().interrupt();
            }
        }
    }
}

