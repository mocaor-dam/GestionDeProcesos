package ejercicio7;

public class Ejercicio7 {
    static void main(String[] args) throws InterruptedException {
        System.out.println("Lanzando hilos..");

        Thread t1 = new Thread(() -> simularTrabajo("Hilo 1"));
        Thread t2 = new Thread(() -> simularTrabajo("Hilo 2"));
        Thread t3 = new Thread(() -> simularTrabajo("Hilo 3"));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Todos los hilos han terminado");
    }

    public static void simularTrabajo(String nombre) {
        try {
            System.out.println(nombre + " iniciado");
            Thread.sleep(2000); // 2 segundos
            System.out.println(nombre + " finalizado");
        } catch (Exception e) {}
    }
}
