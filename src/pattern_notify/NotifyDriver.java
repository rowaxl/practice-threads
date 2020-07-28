package pattern_notify;

import java.util.concurrent.ThreadLocalRandom;

class Counter implements Runnable {
    private boolean isSuspended;

    @Override
    public void run() {
        try {
            for (int i = 20; i > 0; i--) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
                synchronized (this) {
                    while(isSuspended) {
                        System.out.println("Thread " + Thread.currentThread().getName() + " is suspended");
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e);
        }
    }

    public synchronized void setSuspended(boolean suspended) {
        isSuspended = suspended;
        if (!suspended) {
            notify();
        }
    }
}

public class NotifyDriver {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Thread t1 = new Thread(c1, "Counter 1");
        Thread t2 = new Thread(c2, "Counter 2");

        t1.start();
        try {
            Thread.sleep(5000);
            c1.setSuspended(true);

            t2.start();
            Thread.sleep(10000);
            c1.setSuspended(false);
            c2.setSuspended(true);

            Thread.sleep(20000);
            c2.setSuspended(false);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
