package com.ika.servicesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class WorkoutRepository {

    private final ExecutorService executorService;
    private final WorkoutDAO workoutDAO;

    @Inject
    public WorkoutRepository(ExecutorService executorService, WorkoutDAO workoutDAO) {
        this.executorService = executorService;
        this.workoutDAO = workoutDAO;
    }

    public void insert(Workout workout){
        // Prebacujemo insert na drugu nit
        executorService.submit(() -> workoutDAO.insert(workout));
    }

    public List<Workout> getAll(){
        return workoutDAO.getAll();
    }

    public LiveData< List<Workout>> getAllLiveData(){
        return workoutDAO.getAllLiveData();
    }

}
