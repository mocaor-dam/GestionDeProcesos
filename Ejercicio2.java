import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio2 {
    static void main(String[] args) {


        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig");

        try {
            Process proceso = pb.start();

            BufferedReader brOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            String linea;
            System.out.println("SALIDA DEL COMANDO");
            while ((linea = brOut.readLine()) != null){
                System.out.println(linea);
            }


            BufferedReader brErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            System.out.println("Errores: ");
            while((linea = brErr.readLine()) != null){
                System.out.println(linea);
            }

            proceso.waitFor();



        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
