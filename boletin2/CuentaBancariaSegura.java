package boletin2;

class CuentaBancariaSegura {
    private int saldo = 1000;

    // Exclusión mutua: solo un hilo puede ejecutar este método a la vez sobre esta instancia
    public synchronized void retirar(int cantidad, String persona) {
        if (saldo >= cantidad) {
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            saldo -= cantidad;
            System.out.println(persona + " retira " + cantidad + "€. Saldo restante: " + saldo + "€");
        } else {
            System.out.println(persona + " no puede retirar: saldo insuficiente.");
        }
    }

    public int getSaldo() { return saldo; }
}

