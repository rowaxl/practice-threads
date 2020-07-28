package pattern_queue;

class Item {
    private int n;
    private boolean isSet = false;

    public synchronized int getItem() {
        while (!this.isSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e);
            }
        }

        notifyAll();
        this.isSet = false;
        return n;
    }

    public synchronized void setItem(int n) {
        while (this.isSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e);
            }
        }

        notifyAll();
        this.isSet = true;
        this.n = n;
    }
}

class Produce implements Runnable {
    Item target;

    public Produce(Item target) {
        this.target = target;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100) {
            target.setItem(i);
            System.out.println("Set n: " + i);
            i++;
        }
    }
}

class Consume implements Runnable {
    Item target;

    public Consume(Item target) {
        this.target = target;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100) {
            System.out.println("Get n: " + target.getItem());
            i++;
        }
    }
}

public class QueueDriver {

    public static void main(String[] args) {
        Item target = new Item();

        Thread p = new Thread(new Produce(target), "Produce Thread");
        Thread c = new Thread(new Consume(target), "Consume Thread");

        p.start();
        c.start();
    }
}
