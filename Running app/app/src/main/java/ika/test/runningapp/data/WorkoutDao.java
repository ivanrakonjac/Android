package ika.test.runningapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    long insert(Workout workout);

    @Query(value = "SELECT * FROM Workout")
    List<Workout> getAll();
}
