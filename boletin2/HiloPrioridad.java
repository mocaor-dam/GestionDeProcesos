package boletin2;

class HiloPrioridad extends Thread {
    public HiloPrioridad(String nombre, int prioridad) {
        super(nombre);
        setPriority(prioridad);
    }

    @Override
    public void run() {
        long inicio = System.nanoTime();
        long contador = 0;
        for (int i = 0; i < 1_000_000; i++) {
            contador++;
        }
        long fin = System.nanoTime();
        System.out.printf("%s (Prioridad %d) terminó en %.3f ms%n",
                getName(), getPriority(), (fin - inicio) / 1_000_000.0);
    }
}