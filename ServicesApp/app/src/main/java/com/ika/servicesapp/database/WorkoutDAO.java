package com.ika.servicesapp.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface WorkoutDAO {
    @Insert
    long insert(Workout workout);
}
