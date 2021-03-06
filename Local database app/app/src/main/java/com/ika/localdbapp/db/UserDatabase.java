package com.ika.localdbapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ika.localdbapp.MainActivity;
import com.ika.localdbapp.converters.DateConverter;
import com.ika.localdbapp.daos.UserDao;
import com.ika.localdbapp.entities.User;

@TypeConverters(value = {DateConverter.class})
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase userDatabaseInstace = null;

    public abstract UserDao userDao();

    public static UserDatabase getInstance (Context context) {
        if (userDatabaseInstace == null) {
            synchronized (UserDatabase.class) {
                // Double check pattern
                if (userDatabaseInstace == null) {
                    userDatabaseInstace = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user-app.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return userDatabaseInstace;

    }


}
