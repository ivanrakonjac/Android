package com.ika.servicesapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.ika.servicesapp.calories.CaloriesService;
import com.ika.servicesapp.database.Workout;
import com.ika.servicesapp.database.WorkoutRepository;
import com.ika.servicesapp.workouts.WorkoutViewModel;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyReceiver extends BroadcastReceiver {

    @Inject
    public WorkoutViewModel workoutViewModel;

    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED:
                workoutViewModel.insertWorkout(new Workout(0, new Date() ,"ACTION_AIRPLANE_MODE_CHANGED", 10, 15));
                changeNotificationColor(context, R.color.design_default_color_background);
                Log.d("RECEIVER_TAG", "ACTION_AIRPLANE_MODE_CHANGED");
                break;
            case Intent.ACTION_SCREEN_ON:
                workoutViewModel.insertWorkout(new Workout(0, new Date() ,"ACTION_SCREEN_ON", 25, 18));
                changeNotificationColor(context, R.color.material_on_surface_emphasis_medium);
                Log.d("RECEIVER_TAG", "ACTION_SCREEN_ON");
                break;
            default:
                break;
        }
    }

    private Notification geColoredtNotification(Context context, int color){

        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.setAction(CaloriesService.INTENT_ACTION_NOTIFICATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent,0);

        return new androidx.core.app.NotificationCompat.Builder(context,"ServiceAppNotChannel")
                .setSmallIcon(R.drawable.baseline_bar_chart_black_18)
                .setContentTitle("Naslov")
                .setContentText("Content text")
                .setContentIntent(pendingIntent)
                .setColorized(true)
                .setColor(ContextCompat.getColor(context, color))
                .build();
    }

    private void changeNotificationColor(Context context, int color){
        Notification notification = geColoredtNotification(context, color);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }



}