import java.util.concurrent.locks.Lock;

class Filozof extends Thread {
    private final int id;
    private final Lock lWidelec;
    private final Lock pWidelec;

    public Filozof(int id, Lock lWidelec, Lock pWidelec) {
        this.id = id;
        this.lWidelec = lWidelec;
        this.pWidelec = pWidelec;
    }

    private void myslenie() throws InterruptedException {
        System.out.println("Filozof " + id + " mysli.");
        Thread.sleep((int) (Math.random() * 100));
    }

    private void jedzenie() throws InterruptedException {
        System.out.println("Filozof " + id + " je.");
        Thread.sleep((int) (Math.random() * 100));
    }

    @Override
    public void run() {
        try {
            while (true) {
                myslenie();
                if (lWidelec.tryLock()) {
                    try {
                        if (pWidelec.tryLock()) {
                            try {
                                jedzenie();
                            } finally {
                                pWidelec.unlock();
                            }
                        }
                    } finally {
                        lWidelec.unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
