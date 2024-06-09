import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Obiadowanie {
    public static void main(String[] args) {
        int liczbaFilozof = 5;
        Filozof[] filozofs = new Filozof[liczbaFilozof];
        Lock[] widelce = new ReentrantLock[liczbaFilozof];

        for (int i = 0; i < liczbaFilozof; i++) {
            widelce[i] = new ReentrantLock();
        }

        for (int i = 0; i < liczbaFilozof; i++) {
            Lock lWidelec = widelce[i];
            Lock pWidelec = widelce[(i + 1) % liczbaFilozof];
            filozofs[i] = new Filozof(i, lWidelec, pWidelec);
            filozofs[i].start();
        }

        for (int i = 0; i < liczbaFilozof; i++) {
            try {
                filozofs[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
