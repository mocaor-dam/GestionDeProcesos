package boletin2;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Coche extends Thread {
    private final Semaphore parking;
    private final Random random = new Random();

    public Coche(String nombre, Semaphore parking) {
        super(nombre);
        this.parking = parking;
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " llega a la barrera intentando entrar...");
            parking.acquire(); // Solicita 1 permiso (si hay 0, bloquea el hilo)

            System.out.println("--> " + getName() + " ENTRA al parking. Plazas libres: " + parking.availablePermits());

            // Permanece entre 1000 y 3000 ms
            int tiempoEstancia = 1000 + random.nextInt(2001);
            Thread.sleep(tiempoEstancia);

            System.out.println("<-- " + getName() + " SALE del parking tras " + (tiempoEstancia / 1000.0) + "s.");
            parking.release(); // Libera el permiso para el siguiente coche

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

