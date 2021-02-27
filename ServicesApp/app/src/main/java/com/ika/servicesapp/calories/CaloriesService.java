package com.ika.servicesapp.calories;

import android.app.Notification;
import android.app.PendingIntent;
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

import com.ika.servicesapp.MainActivity;
import com.ika.servicesapp.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class CaloriesService extends Service {

    private final Timer timer = new Timer();

    private boolean serviceStarted = false;


    public static final String INTENT_ACTION_START = "com.ika.servicesapp.START";
    public static final String INTENT_ACTION_POWER = "com.ika.servicesapp.POWER";
    public static final String INTENT_ACTION_NOTIFICATION = "com.ika.servicesapp.NOTIFICATION";

    public static final String LOG_TAG = "CALORIES_SERVICE_TAG";

    private final AtomicReference<String> motivationMessage = new AtomicReference<>("Trci brze!");

    private void scheduleTimer(){
        Handler handler = new Handler(Looper.getMainLooper());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(()->{
                    Toast.makeText(CaloriesService.this,motivationMessage.get(), Toast.LENGTH_SHORT).show();
                });
            }
        },0, 7000);

        serviceStarted = true;
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "onCreateCommand");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");

        //NotificationManagerCompat.from(this).notify(1, getNotification());
        createNotificationChannel();
        startForeground(1, getNotification());

        switch (intent.getAction()){
            case INTENT_ACTION_START:
                if(!serviceStarted){
                    scheduleTimer();
                }
                break;
            case INTENT_ACTION_POWER:
                if(serviceStarted){
                    changeMotivationMessage();
                }

                break;
            default:
                break;
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");

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

        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.setAction(INTENT_ACTION_NOTIFICATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,0);

        return new androidx.core.app.NotificationCompat.Builder(this,"ServiceAppNotChannel")
                                                .setSmallIcon(R.drawable.baseline_bar_chart_black_18)
                                                .setContentTitle("Naslov")
                                                .setContentText("Content text")
                                                .setContentIntent(pendingIntent)
                                                .setColorized(true)
                                                .setColor(ContextCompat.getColor(this,R.color.teal_200))
                                                .build();
    }

    private void changeMotivationMessage(){
        motivationMessage.set("Trci visee!");
    }
}