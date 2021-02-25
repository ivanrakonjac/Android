package com.ika.servicesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public class WorkoutRepository {

    private final WorkoutDAO workoutDAO;

    public WorkoutRepository(WorkoutDAO workoutDAO) {
        this.workoutDAO = workoutDAO;
    }

    public long insert(Workout workout){
        return workoutDAO.insert(workout);
    }

    public List<Workout> getAll(){
        return workoutDAO.getAll();
    }

    public LiveData< List<Workout>> getAllLiveData(){
        return workoutDAO.getAllLiveData();
    }

}
