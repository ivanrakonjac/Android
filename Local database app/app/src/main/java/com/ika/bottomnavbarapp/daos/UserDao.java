package com.ika.bottomnavbarapp.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ika.bottomnavbarapp.entities.User;

@Dao
public interface UserDao {

    @Insert
    long insert (User user);

}
