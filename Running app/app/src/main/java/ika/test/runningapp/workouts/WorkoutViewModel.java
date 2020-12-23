package ika.test.runningapp.workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ika.test.runningapp.data.Workout;
import ika.test.runningapp.data.WorkoutRepository;

public class WorkoutViewModel extends ViewModel {

    private final WorkoutRepository workoutRepository;

    public WorkoutViewModel(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public void insertWorkout(Workout workout){
        workoutRepository.insert(workout);
    }

    public LiveData<List<Workout>> getWorkoutList(){
        return workoutRepository.getAllLiveData();
    }
}
