package com.ika.servicesapp.calories;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.media.app.NotificationCompat;

import com.ika.servicesapp.R;

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

        //NotificationManagerCompat.from(this).notify(1, getNotification());
        createNotificationChannel();
        startForeground(1, getNotification());

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

    private void createNotificationChannel(){
        NotificationChannelCompat notificationChannel = new NotificationChannelCompat
                .Builder("ServiceAppNotChannel",NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setName("CHANNEL_MAIN")
                .build();
        NotificationManagerCompat.from(this).createNotificationChannel(notificationChannel);
    }

    private Notification getNotification(){
        return new androidx.core.app.NotificationCompat.Builder(this,"ServiceAppNotChannel")
                                                .setSmallIcon(R.drawable.baseline_bar_chart_black_18)
                                                .setContentTitle("Naslov")
                                                .setContentText("Content text")
                                                .setColorized(true)
                                                .setColor(ContextCompat.getColor(this,R.color.teal_200))
                                                .build();
    }
}