package com.ika.threading.threads;

import java.util.concurrent.LinkedBlockingDeque;

public class CustomDequeThread extends Thread{
    private LinkedBlockingDeque<Runnable> runnableDeque = new LinkedBlockingDeque<>();

    public LinkedBlockingDeque<Runnable> getRunnableDeque() {
        return runnableDeque;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Runnable runnable = runnableDeque.takeFirst(); //blokirajuci poziv

                runnable.run();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
