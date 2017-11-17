package com.example.springboot;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by puroc on 17/5/23.
 */
public class Counter {

    private Timer timer = new Timer();

    private AtomicLong num = new AtomicLong(0);

    private static final Counter COUNTER = new Counter();

    private Counter() {
        //no instance
    }

    public static Counter getInstance() {
        return COUNTER;
    }

    public void start() {
        timer.schedule(new MyTimerTask(), 0, 1000);
    }

    public long add() {
        return num.incrementAndGet();
    }

    public void clean() {
        num.set(0);
    }

    public long get() {
        return num.get();
    }

    public void stop() {
        timer.cancel();
    }

    class MyTimerTask extends TimerTask {

        public void run() {
            System.out.println("rate:" + get());
            clean();
        }
    }
}
