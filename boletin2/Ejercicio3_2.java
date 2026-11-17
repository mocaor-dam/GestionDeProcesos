package boletin2;

import java.util.Random;

public class Ejercicio3_2 {
    public static void main(String[] args) {
        BufferCompartido buffer = new BufferCompartido();
        Random random = new Random();

        Thread productor = new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    buffer.producir(random.nextInt(100));
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    buffer.consumir();
                    Thread.sleep(450); // Consume más lento para ver cómo se llena el buffer
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        productor.start();
        consumidor.start();
    }
}