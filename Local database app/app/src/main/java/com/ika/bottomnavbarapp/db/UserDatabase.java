package com.ika.bottomnavbarapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.ika.bottomnavbarapp.converters.DateConverter;
import com.ika.bottomnavbarapp.daos.UserDao;
import com.ika.bottomnavbarapp.entities.User;

@TypeConverters(value = {DateConverter.class})
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();


}
