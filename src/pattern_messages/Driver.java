package pattern_messages;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        Message msg = new Message();

        Thread t1 = new Thread(new MessageRunnable(msg, "First message"));

        Thread t2 = new Thread(new MessageRunnable(msg, "Second message"));

        Thread t3 = new Thread(new MessageRunnable(msg, "Third message"));

        t1.start();
        t2.start();
        t3.start();
    }


}
