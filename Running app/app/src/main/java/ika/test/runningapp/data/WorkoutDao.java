package ika.test.runningapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WorkoutDao {

    @Insert
    long insert(Workout workout);

}
