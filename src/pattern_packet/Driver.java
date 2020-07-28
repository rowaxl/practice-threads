package pattern_packet;

public class Driver {
    public static void main(String[] args) {
        Data data = new Data();

        Thread sender = new Thread(new Sender(data), "Sending Thread");
        Thread receiver = new Thread(new Receiver(data), "Receiving Thread");

        sender.start();
        receiver.start();
    }
}
