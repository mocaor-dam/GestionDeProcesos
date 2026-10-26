package ejercicio8;

public class Calculador {
    static void main(String[] args) {
        if (args.length < 1) return;
        int num = Integer.parseInt(args[0]);
        System.out.println("Cuadrado de " + num + " = " + (num * num));
    }
}
