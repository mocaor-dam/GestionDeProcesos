package boletin2;

import java.util.concurrent.Semaphore;

public class Ejercicio3_3 {
    public static void main(String[] args) {
        // Semáforo con 3 permisos (el parámetro 'true' garantiza orden FIFO / justicia)
        Semaphore aparcamiento = new Semaphore(3, true);

        for (int i = 1; i <= 8; i++) {
            new Coche("Coche-" + i, aparcamiento).start();
        }
    }
}
