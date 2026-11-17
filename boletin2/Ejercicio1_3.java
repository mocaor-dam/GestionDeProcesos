package boletin2;

import java.util.Scanner;

public class Ejercicio1_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce N1: ");
        long n1 = sc.nextLong();
        System.out.print("Introduce N2: ");
        long n2 = sc.nextLong();

        long inicio = System.nanoTime();

        Sumador hilo1 = new Sumador(n1);
        Sumador hilo2 = new Sumador(n2);

        hilo1.start();
        hilo2.start();

        try {
            // El hilo main espera a que ambos terminen
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long fin = System.nanoTime();
        double tiempoMs = (fin - inicio) / 1_000_000.0;

        System.out.println("Sumatorio 1 hasta " + n1 + " = " + hilo1.getResultado());
        System.out.println("Sumatorio 1 hasta " + n2 + " = " + hilo2.getResultado());
        System.out.printf("Tiempo total de ejecución: %.3f ms%n", tiempoMs);
        sc.close();
    }
}