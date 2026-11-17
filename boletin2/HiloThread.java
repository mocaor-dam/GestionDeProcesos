package boletin2;

// 1. Heredando de Thread
class HiloThread extends Thread {
    public HiloThread(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - Iteración " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(getName() + " interrumpido.");
                Thread.currentThread().interrupt();
            }
        }
    }
}

// 2. Implementando Runnable
class HiloRunnable implements Runnable {
    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre + " - Iteración " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(nombre + " interrumpido.");
                Thread.currentThread().interrupt();
            }
        }
    }
}

