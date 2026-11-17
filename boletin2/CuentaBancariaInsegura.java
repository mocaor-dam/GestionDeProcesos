package boletin2;

class CuentaBancariaInsegura {
    private int saldo = 1000;

    public void retirar(int cantidad, String persona) {
        if (saldo >= cantidad) {
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            saldo -= cantidad;
            System.out.println(persona + " retira " + cantidad + "€. Saldo restante: " + saldo + "€");
        }
    }

    public int getSaldo() { return saldo; }
}