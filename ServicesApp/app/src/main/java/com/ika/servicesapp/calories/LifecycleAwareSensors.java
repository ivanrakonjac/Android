package com.ika.servicesapp.calories;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;

import com.ika.servicesapp.MainActivity;

import java.util.List;

import javax.inject.Inject;

public class LifecycleAwareSensors implements DefaultLifecycleObserver {

    private  SensorManager sensorManager = null;

    @Inject
    public LifecycleAwareSensors() {

    }

    public void startMeasuring(Context context){
       sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            Log.d("SENSOR_LOG_TAG", sensor.toString());
        }
    }

}
