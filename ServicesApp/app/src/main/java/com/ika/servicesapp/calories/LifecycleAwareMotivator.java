package com.ika.servicesapp.calories;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

public class LifecycleAwareMotivator implements DefaultLifecycleObserver {

    private final Timer timer = new Timer();

    private final AtomicReference<String> motivationMessage = new AtomicReference<>("Trci brze!");

    @Inject
    public LifecycleAwareMotivator() {

    }

    public void scheduleTimer(Context context){
        Handler handler = new Handler(Looper.getMainLooper());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(()->{
                    Toast.makeText(context,motivationMessage.get(), Toast.LENGTH_SHORT).show();
                });
            }
        },0, 7000);

    }

    public void changeMotivationMessage(){
        motivationMessage.set("Trci visee!");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        timer.cancel();
        CaloriesService.serviceStarted = false;
    }
}
