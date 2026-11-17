package boletin2;

public class Ejercicio2_2 {
    public static void main(String[] args) {
        HiloPrioridad hMin = new HiloPrioridad("Hilo-MIN", Thread.MIN_PRIORITY);   // 1
        HiloPrioridad hNorm = new HiloPrioridad("Hilo-NORM", Thread.NORM_PRIORITY); // 5
        HiloPrioridad hMax = new HiloPrioridad("Hilo-MAX", Thread.MAX_PRIORITY);   // 10

        hMin.start();
        hNorm.start();
        hMax.start();
    }
}
