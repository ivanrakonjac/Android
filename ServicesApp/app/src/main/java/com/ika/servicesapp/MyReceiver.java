package com.ika.servicesapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

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
                Log.d("RECEIVER_TAG", "ACTION_AIRPLANE_MODE_CHANGED");
                break;
            case Intent.ACTION_SCREEN_ON:
                workoutViewModel.insertWorkout(new Workout(0, new Date() ,"ACTION_SCREEN_ON", 25, 18));
                Log.d("RECEIVER_TAG", "ACTION_SCREEN_ON");
                break;
            default:
                break;
        }
    }
}