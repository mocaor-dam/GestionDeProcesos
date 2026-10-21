package ejercicio7;

public class Ejercicio7B {
    public static void main(String[] args) throws Exception {
        System.out.println("Lanzando procesos...");

        // Necesitamos llamar a un programa externo (Tarea.java)
        ProcessBuilder pb1 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "Proceso 1", "2");
        ProcessBuilder pb2 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "Proceso 2", "2");
        ProcessBuilder pb3 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "Proceso 3", "2");

        // Los arrancamos a la vez
        Process p1 = pb1.inheritIO().start();
        Process p2 = pb2.inheritIO().start();
        Process p3 = pb3.inheritIO().start();

        // Esperamos a que terminen
        p1.waitFor();
        p2.waitFor();
        p3.waitFor();

        System.out.println("Todos los procesos han terminado.");
    }
}
