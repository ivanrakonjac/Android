package com.ika.servicesapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Database;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.servicesapp.MainActivity;
import com.ika.servicesapp.database.ServiceAppDatabase;
import com.ika.servicesapp.database.Workout;
import com.ika.servicesapp.databinding.FragmentWorkoutsBinding;
import com.ika.servicesapp.utils.WorkoutAdapter;

import java.util.Date;
import java.util.List;


public class WorkoutsFragment extends Fragment {

    FragmentWorkoutsBinding binding;
    MainActivity mainActivity;

    ServiceAppDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkoutsBinding.inflate(inflater, container, false);

        database = ServiceAppDatabase.getInstance(mainActivity.getApplicationContext());

        WorkoutAdapter workoutAdapter = new WorkoutAdapter();
        workoutAdapter.setWorkoutList(database.workoutDao().getAll());


        binding.floatingActionButton.setOnClickListener( view -> {
            database.workoutDao().insert(new Workout(0, new Date() ,"Proba", 25.0, 11));
        });

        binding.recyclerView.setAdapter(workoutAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        return binding.getRoot();
    }
}