package boletin2;

import java.util.LinkedList;
import java.util.Queue;

class BufferCompartido {
    private final Queue<Integer> cola = new LinkedList<>();
    private final int CAPACIDAD = 5;

    public synchronized void producir(int valor) throws InterruptedException {
        while (cola.size() == CAPACIDAD) {
            System.out.println("Buffer lleno. Productor esperando...");
            wait();
        }
        cola.add(valor);
        System.out.println("Producido: " + valor + " | Elementos en buffer: " + cola.size());
        notifyAll(); // Despierta a los consumidores que estén esperando
    }

    public synchronized int consumir() throws InterruptedException {
        while (cola.isEmpty()) {
            System.out.println("Buffer vacío. Consumidor esperando...");
            wait();
        }
        int valor = cola.poll();
        System.out.println("Consumido: " + valor + " | Elementos en buffer: " + cola.size());
        notifyAll(); // Despierta a los productores que estén esperando
        return valor;
    }
}