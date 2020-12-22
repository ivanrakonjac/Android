package ika.test.runningapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters(value = {DateConverter.class})
@Database(entities = {Workout.class}, version = 1, exportSchema = false)
public abstract class RunDatabase extends RoomDatabase {

    public abstract WorkoutDao workoutDao();

    private static final String DATABASE_NAME = "run-app.db";
    public static RunDatabase instance = null;

    public static RunDatabase getInstance(Context context){
        if(instance == null){
            synchronized (RunDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), RunDatabase.class, "run-app.db")
                            //allowMainThreadQueries() nije NIKAKO pametno raditi => samo za trenutno testiranje
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }

}
