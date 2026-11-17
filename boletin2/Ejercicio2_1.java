package boletin2;

public class Ejercicio2_1 {
    public static void main(String[] args) throws InterruptedException {
        Thread trabajador = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 1. Recién instanciado
        System.out.println("1. Tras instanciar: " + trabajador.getState()); // NEW

        // 2. Tras invocar start()
        trabajador.start();
        System.out.println("2. Tras start(): " + trabajador.getState()); // RUNNABLE

        // 3. Esperamos 500ms para asegurarnos de que entró en el sleep(1000)
        Thread.sleep(500);
        System.out.println("3. Durante el sleep: " + trabajador.getState()); // TIMED_WAITING

        // 4. Esperamos a que termine su ejecución
        trabajador.join();
        System.out.println("4. Al finalizar: " + trabajador.getState()); // TERMINATED
    }
}