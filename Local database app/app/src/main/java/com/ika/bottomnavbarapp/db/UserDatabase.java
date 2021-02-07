package com.ika.bottomnavbarapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ika.bottomnavbarapp.daos.UserDao;
import com.ika.bottomnavbarapp.entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();


}
