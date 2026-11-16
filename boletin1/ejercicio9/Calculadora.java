package boletin1.ejercicio9;

public class Calculadora {
    static void main(String[] args) {
        if (args.length < 2 ) return;

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        int suma = num1 + num2;

        System.out.println(suma);
    }
}
