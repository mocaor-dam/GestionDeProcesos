package ejercicio6;

import java.io.IOException;

public class PrincipalEJ6 {
    static void main(String[] args) throws Exception {
        System.out.println("PRUEBA SECUENCIAL");

        long inicioSeq = System.currentTimeMillis();

        Process p1 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "1", "4").inheritIO().start();
        p1.waitFor();

        Process p2 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "2", "4").inheritIO().start();
        p2.waitFor();

        Process p3 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "3", "4").inheritIO().start();
        p3.waitFor();

        long finSeq = System.currentTimeMillis();
        System.out.println("Tiempo total secuencial: " + (finSeq - inicioSeq) + " ms\n");


        System.out.println("Prueba paralela");
        long inicioPar = System.currentTimeMillis();

        Process p4 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "4", "4").inheritIO().start();
        Process p5 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "5", "4").inheritIO().start();
        Process p6 = new ProcessBuilder("java", "src/ejercicio5/Tarea.java", "6", "4").inheritIO().start();

        p4.waitFor();
        p5.waitFor();
        p6.waitFor();


    long finPar = System.currentTimeMillis();

        System.out.println("Tiempo total paralelo: " + (finPar - inicioPar) + " ms");

    }
}
