package pattern_messages;

class Message {
    boolean isSending = false;

    public synchronized void send(String message) {
        System.out.println("[Sending message: " + message);

        isSending = true;
        while(isSending) {
            System.out.println("wait for sending");
            try {
                Thread.sleep(2000);
                isSending = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupted();
                System.out.println("Thread interrupted: " + e);
            }
        }


        System.out.printf("Message sent!: %s]\n", message);
        System.out.println("");
    }

}

public class MessageRunnable implements Runnable {
    private Message target;
    private String message;

    public MessageRunnable(Message target, String message) {
        this.target = target;
        this.message = message;
    }

    @Override
    public void run() {
        synchronized (this) {
            target.send(message);
        }
    }
}
