package com.ika.servicesapp.database;

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
}
