package boletin2;

public class Ejercicio3_1 {
    public static void main(String[] args) throws InterruptedException {
        CuentaBancariaSegura cuenta = new CuentaBancariaSegura();

        Runnable tareaRetirada = () -> {
            for (int i = 0; i < 10; i++) {
                cuenta.retirar(50, Thread.currentThread().getName());
            }
        };

        Thread p1 = new Thread(tareaRetirada, "Persona-1");
        Thread p2 = new Thread(tareaRetirada, "Persona-2");

        p1.start();
        p2.start();
        p1.join();
        p2.join();

        // Con 20 retiradas de 50€ sobre 1000€, el saldo final exacto debe ser 0€
        System.out.println("Saldo final en cuenta: " + cuenta.getSaldo() + "€");
    }
}
