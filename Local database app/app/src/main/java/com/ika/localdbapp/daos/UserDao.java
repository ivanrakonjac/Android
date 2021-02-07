package com.ika.localdbapp.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.ika.localdbapp.entities.User;

@Dao
public interface UserDao {

    @Insert
    long insert (User user);

}
