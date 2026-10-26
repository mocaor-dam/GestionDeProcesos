package ejercicio1;

import java.io.IOException;

public class Ejercicio1 {
    static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");

        try {
            Process proceso = pb.start();
            System.out.println("Proceso iniciado con PID: " + proceso.pid());

            int exitCode = proceso.waitFor();
            System.out.println("El proceso finalizó con código de salida: " + exitCode);

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
