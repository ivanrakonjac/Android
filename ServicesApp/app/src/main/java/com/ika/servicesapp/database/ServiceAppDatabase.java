package com.ika.servicesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters(value = {DateConverter.class})
@Database(entities = {Workout.class}, version = 1, exportSchema = false)
public abstract class ServiceAppDatabase extends RoomDatabase {

    public abstract WorkoutDAO workoutDao();

    private static final String DATABASE_NAME = "service-app.db";
    private static ServiceAppDatabase instance = null;

    public static ServiceAppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ServiceAppDatabase.class) {
                if (instance == null) {

                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ServiceAppDatabase.class,
                            DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }

}
