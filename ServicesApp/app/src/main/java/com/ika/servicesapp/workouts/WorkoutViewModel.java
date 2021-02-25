package com.ika.servicesapp.workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ika.servicesapp.database.Workout;
import com.ika.servicesapp.database.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends ViewModel {

    private final WorkoutRepository workoutRepository;

    public WorkoutViewModel(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public void insertWorkout(Workout workout){
        workoutRepository.insert(workout);
    }

    public List<Workout> getAll(){
        return workoutRepository.getAll();
    }

    public LiveData< List<Workout>> getAllLiveData(){
        return workoutRepository.getAllLiveData();
    }
}
