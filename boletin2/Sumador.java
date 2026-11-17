package boletin2;

import java.util.Scanner;

class Sumador extends Thread {
    private final long limite;
    private long resultado = 0;

    public Sumador(long limite) {
        this.limite = limite;
    }

    @Override
    public void run() {
        for (long i = 1; i <= limite; i++) {
            resultado += i;
        }
    }

    public long getResultado() {
        return resultado;
    }
}

