package com.ika.servicesapp.calories;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.ika.servicesapp.MainActivity;

import java.util.List;

import javax.inject.Inject;

public class LifecycleAwareSensors implements DefaultLifecycleObserver {

    private  SensorManager sensorManager = null;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            Log.d("SENSOR_LOG_TAG", "temp_sensor:" + event.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Inject
    public LifecycleAwareSensors() {

    }

    public void startMeasuring(Context context){
       sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        /*List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            Log.d("SENSOR_LOG_TAG", sensor.toString());
        }*/

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if(sensorManager != null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
}
