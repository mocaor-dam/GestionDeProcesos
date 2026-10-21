package ejercicio4;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class PadreEJ4 {
    public static void main(String[] args) throws Exception {
        // Preparamos y arrancamos el proceso hijo
        ProcessBuilder pb = new ProcessBuilder("java", "src/ejercicio4/HijoEJ4.java");
        Process proceso = pb.start();

        // ENVIAR DATOS AL HIJO
        OutputStream os = proceso.getOutputStream();
        // Escribimos un 10 seguido de un salto de línea (\n) para que el Scanner del hijo sepa que hemos terminado de escribir
        os.write("10\n".getBytes());
        os.flush(); // Obliga a enviar los datos inmediatamente
        os.close(); // Cerramos nuestra escritura

        //  LEER LA RESPUESTA DEL HIJO
        InputStream is = proceso.getInputStream();

            Scanner sc = new Scanner(is);

            int n = sc.nextInt();

        System.out.println(n);

        //  Esperamos a que el proceso termine
        proceso.waitFor();
    }
}