package com.ika.servicesapp.calories;

import android.content.Intent;
import android.os.Bundle;

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
            startWorkout();
        });

        binding.stopCaloriesService.setOnClickListener( v -> {
            stopWorkout();
        });

        return binding.getRoot();
    }

    private void startWorkout(){
        Intent intent = new Intent();
        intent.setClass(mainActivity, CaloriesService.class);
        mainActivity.startService(intent);
    }

    private void stopWorkout() {
        Intent intent = new Intent();
        intent.setClass(mainActivity, CaloriesService.class);
        mainActivity.stopService(intent);
    }

}