package com.ika.servicesapp.di;

import android.content.Context;

import com.ika.servicesapp.database.ServiceAppDatabase;
import com.ika.servicesapp.database.WorkoutDAO;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public interface DatabaseModule {

    @Provides
    static WorkoutDAO providesWorkoutDAO(@ApplicationContext Context context) {
        return ServiceAppDatabase.getInstance(context).workoutDao();
    }

}
