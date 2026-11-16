package boletin1.ejercicio5;

public class PrincipalEJ5 {
    static void main(String[] args) throws Exception {
        ProcessBuilder pbA = new ProcessBuilder("java", "src/boletin1.ejercicio5/Tarea.java", "A", "5");
        ProcessBuilder pbB = new ProcessBuilder("java", "src/boletin1.ejercicio5/Tarea.java", "B", "3");
        ProcessBuilder pbC = new ProcessBuilder("java", "src/boletin1.ejercicio5/Tarea.java", "C", "2");

        Process procA= pbA.inheritIO().start();
        Process procB= pbB.inheritIO().start();
        Process procC= pbC.inheritIO().start();

        procA.waitFor();
        procB.waitFor();
        procC.waitFor();

        System.out.println("Todos los procesos han terminado");

    }



}
