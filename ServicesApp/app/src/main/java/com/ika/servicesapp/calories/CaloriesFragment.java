package com.ika.servicesapp.calories;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.servicesapp.MainActivity;
import com.ika.servicesapp.R;
import com.ika.servicesapp.database.Workout;
import com.ika.servicesapp.databinding.FragmentCaloriesBinding;
import com.ika.servicesapp.databinding.FragmentWorkoutsBinding;

public class CaloriesFragment extends Fragment {

    FragmentCaloriesBinding binding;
    MainActivity mainActivity;


    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                    new ActivityResultCallback<Boolean>() {
                        @Override
                        public void onActivityResult(Boolean isPermissionGranted) {
                            if(isPermissionGranted){
                                startWorkout();
                                // Ako zelim da se aplikacija odmah nakon porektanja zatvori
                                mainActivity.finish();
                            }
                        }
                    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCaloriesBinding.inflate(inflater, container, false);

        binding.startCaloriesService.setOnClickListener( v -> {
            if(ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }else{
                startWorkout();
                // Ako zelim da se aplikacija odmah nakon porektanja zatvori
                mainActivity.finish();
            }

        });

        binding.stopCaloriesService.setOnClickListener( v -> {
            stopWorkout();
        });

        binding.powerCaloriesService.setOnClickListener( v -> {
            powerWorkout();
        } );

        return binding.getRoot();
    }

    private void startWorkout(){
        Intent intent = new Intent();
        intent.setClass(mainActivity, CaloriesService.class);
        intent.setAction(CaloriesService.INTENT_ACTION_START);
        mainActivity.startService(intent);
    }

    private void stopWorkout() {
        Intent intent = new Intent();
        intent.setClass(mainActivity, CaloriesService.class);
        mainActivity.stopService(intent);
    }

    private void powerWorkout(){
        Intent intent = new Intent();
        intent.setClass(mainActivity, CaloriesService.class);
        intent.setAction(CaloriesService.INTENT_ACTION_POWER);
        mainActivity.startService(intent);
    }

}