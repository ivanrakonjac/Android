package ika.test.runningapp.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import ika.test.runningapp.data.RunDatabase;
import ika.test.runningapp.data.Workout;
import ika.test.runningapp.data.WorkoutDao;

@Module
@InstallIn({SingletonComponent.class})
public interface DatabaseModule {

    @Provides
    static WorkoutDao provideWorkoutDao(@ApplicationContext Context context){
        return RunDatabase.getInstance(context).workoutDao();
    }

}
