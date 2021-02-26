package com.ika.servicesapp.workouts;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ika.servicesapp.database.Workout;
import com.ika.servicesapp.database.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends ViewModel {

    private final WorkoutRepository workoutRepository;

    @ViewModelInject
    public WorkoutViewModel(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public long insertWorkout(Workout workout){
        return workoutRepository.insert(workout);
    }

    public List<Workout> getAll(){
        return workoutRepository.getAll();
    }

    public LiveData< List<Workout>> getAllLiveData(){
        return workoutRepository.getAllLiveData();
    }
}
