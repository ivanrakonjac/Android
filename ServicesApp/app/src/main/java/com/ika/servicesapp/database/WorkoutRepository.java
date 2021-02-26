package com.ika.servicesapp.database;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class WorkoutRepository {

    private final ExecutorService executorService;
    private final WorkoutDAO workoutDAO;

    @Inject
    public WorkoutRepository(ExecutorService executorService, WorkoutDAO workoutDAO) {
        this.executorService = executorService;
        this.workoutDAO = workoutDAO;
    }

    public long insert(Workout workout){
        // Prebacujemo insert na drugu nit
        Future<Long> future = executorService.submit(() -> workoutDAO.insert(workout));

        try {
            return executorService.submit( () ->  future.get()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Workout> getAll(){
        return workoutDAO.getAll();
    }

    public LiveData< List<Workout>> getAllLiveData(){
        return workoutDAO.getAllLiveData();
    }

}
