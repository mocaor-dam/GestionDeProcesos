package ejercicio3;

import java.io.IOException;

public class Padre {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java", "src/ejercicio3/Hijo.java", "10");

        try {
            int res = pb.start().waitFor();

            System.out.println(res);
        } catch (IOException  | InterruptedException e) {
            System.out.println(e.getMessage());
        }








    }


}
