package ejercicio5;

public class Tarea {
    static void main(String[] args) throws InterruptedException {
        String nombre = args[0];
        int segundos = Integer.parseInt(args[1]);


        System.out.println("El proceso " + nombre + " iniciado");

        Thread.sleep(segundos * 1000);

        System.out.println("Proceso " + nombre + " finalizado");
    }
}
