package com.ika.servicesapp.calories;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class CaloriesService extends Service {

    private final Timer timer = new Timer();

    private boolean serviceStarted = false;

    private void scheduleTimer(){
        Handler handler = new Handler(Looper.getMainLooper());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(()->{
                    Toast.makeText(CaloriesService.this,"Trciii!", Toast.LENGTH_SHORT).show();
                });
            }
        },0, 7000);

        serviceStarted = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICES", "onStartCommand");
        if(!serviceStarted){
            scheduleTimer();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        serviceStarted = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}