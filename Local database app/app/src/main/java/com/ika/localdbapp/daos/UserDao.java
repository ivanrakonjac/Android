package com.ika.localdbapp.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ika.localdbapp.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insert (User user);

    @Query("SELECT * FROM User")
    List<User> getAll ();

}
