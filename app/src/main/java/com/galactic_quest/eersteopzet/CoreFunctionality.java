package com.galactic_quest.eersteopzet;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Thomas on 20-9-2015.
 */

public class CoreFunctionality extends Service {

    volatile boolean playingGalactic;
    long lastTick;
    public int tickNumber;
    int tickLength;
    Thread tickThread;

    final class TickThreadClass implements Runnable {
        int tick_id;

        TickThreadClass(int tick_id){
            this.tick_id = tick_id;

        }

        @Override
        public void run() {

            while (playingGalactic) {
                controlTicks();
                tickNumber++;
                //sendTick(tickNumber);
                Log.i("Tick nummer ", "" + tickNumber);
            }

        }

        public void controlTicks() {

            tickLength = 2000;
            long timeThisTick = (System.currentTimeMillis() - lastTick); // uitrekenen lengte deze tick
            long timeToWait = tickLength - timeThisTick; // tick duurt 2000 ms dus tijd om te wachten is 2000 minus de duur van de tick (als deze nog niet voorbij is)
            if (timeToWait > 0) {
                try {
                    tickThread.sleep(timeToWait);
                } catch (InterruptedException e) {
                }
            }
            lastTick = System.currentTimeMillis();
            if (tickNumber > 10) {
                playingGalactic = false;
                stopSelf(tick_id);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        playingGalactic = true;
        Thread tickThread = new Thread(new TickThreadClass(startId));
        tickThread.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("debug", " nu in onBind CoreF" );
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i("debug", " nu in onDestroy CoreF" );
        super.onDestroy();
    }
}