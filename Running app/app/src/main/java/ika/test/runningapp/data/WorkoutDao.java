package ika.test.runningapp.data;

import androidx.lifecycle.LiveData;
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

    @Query(value = "SELECT * FROM Workout")
    LiveData<List<Workout>> getAllLiveData();
}
