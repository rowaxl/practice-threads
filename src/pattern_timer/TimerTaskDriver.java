package pattern_timer;

import java.util.Timer;
import java.util.TimerTask;

class TickTok extends TimerTask {
    boolean tick = true;

    @Override
    public void run() {
        System.out.println(tick ? "Tick" : "Tok");
        this.tick = !this.tick;
    }
}

public class TimerTaskDriver {
    static final Timer timer = new Timer();

    public static void main(String[] args) {
        TickTok t = new TickTok();

        timer.schedule(t, 5000, 1000);
    }
}
