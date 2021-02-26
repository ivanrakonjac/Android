package com.ika.servicesapp.workouts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.servicesapp.MainActivity;
import com.ika.servicesapp.database.ServiceAppDatabase;
import com.ika.servicesapp.database.Workout;
import com.ika.servicesapp.database.WorkoutRepository;
import com.ika.servicesapp.databinding.FragmentWorkoutsBinding;
import com.ika.servicesapp.workouts.WorkoutAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WorkoutsFragment extends Fragment {

    private FragmentWorkoutsBinding binding;
    private MainActivity mainActivity;
    private List<Workout> workoutList = new ArrayList<>();
    private WorkoutViewModel workoutViewModel;


    ServiceAppDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) requireActivity();

        /*ServiceAppDatabase database = ServiceAppDatabase.getInstance(mainActivity);
        WorkoutRepository workoutRepository = new WorkoutRepository(database.workoutDao());
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new WorkoutViewModel(workoutRepository);
            }
        };*/
        workoutViewModel = new ViewModelProvider(mainActivity).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkoutsBinding.inflate(inflater, container, false);

        WorkoutAdapter workoutAdapter = new WorkoutAdapter();
        workoutViewModel.getAllLiveData().observe(getViewLifecycleOwner(), workoutList -> {
            workoutAdapter.setWorkoutList(workoutList);
        });


        binding.floatingActionButton.setOnClickListener( view -> {
            workoutViewModel.insertWorkout(new Workout(0, new Date() ,"ExecutorServiceTest", 11, 15));
        });

        binding.recyclerView.setAdapter(workoutAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        return binding.getRoot();
    }
}