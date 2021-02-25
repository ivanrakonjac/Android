package com.ika.servicesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WorkoutDAO {
    @Insert
    long insert(Workout workout);

    @Query("SELECT * FROM Workout")
    List<Workout> getAll();

    @Query("SELECT * FROM Workout")
    LiveData< List<Workout>> getAllLiveData();
}
