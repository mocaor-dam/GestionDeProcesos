package boletin2;

/**
 * Clase que demuestra la prevención de interbloqueos (Deadlocks) mediante
 * la imposición de un orden global estricto en la adquisición de monitores.
 *
 * @author Moises Cabanillas Ortega
 * @version 1.0
 */
public class DeadlockResuelto {

    /** Primer recurso compartido en la jerarquía de bloqueo. */
    private static final Object RecursoA = new Object();

    /** Segundo recurso compartido en la jerarquía de bloqueo. */
    private static final Object RecursoB = new Object();

    /**
     * Punto de entrada principal. Inicia dos hilos concurrentes que acceden
     * a dos recursos compartidos sin provocar interbloqueo.
     * Mecanismo de seguridad implantado: Para romper la condición de
     * espera circular, ambos hilos siguen estrictamente el mismo orden
     * de adquisición de bloqueos ( RecursoA ->  RecursoB). De este
     * modo, si un hilo posee  RecursoB, se garantiza que ya posee
     * RecursoA, evitando que dos hilos queden bloqueados mutuamente.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> ejecutarTarea("Hilo 1"));
        Thread t2 = new Thread(() -> ejecutarTarea("Hilo 2"));

        t1.start();
        t2.start();
    }

    /**
     * Ejecuta una sección crítica anidada respetando la jerarquía de bloqueos.
     *
     * @param nombreHilo Identificador del hilo para trazar la ejecución en consola.
     */
    private static void ejecutarTarea(String nombreHilo) {
        synchronized (RecursoA) {
            System.out.println(nombreHilo + ": Bloqueó Recurso A");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (RecursoB) {
                System.out.println(nombreHilo + ": Bloqueó Recurso B");
            }
            System.out.println(nombreHilo + ": Liberó Recurso B");
        }
        System.out.println(nombreHilo + ": Liberó Recurso A");
    }
}
