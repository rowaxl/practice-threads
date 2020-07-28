package pattern_packet;

public class Data {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean isTransferring = true;

    public synchronized void send(String packet) {
        while(!isTransferring) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted " + e);
            }
        }

        isTransferring = false;
        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while (isTransferring) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted " + e);
            }
        }

        isTransferring = true;
        notifyAll();
        return packet;
    }
}
