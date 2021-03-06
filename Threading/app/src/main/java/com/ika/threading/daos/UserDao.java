package com.ika.threading.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ika.threading.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insert (User user);

    @Query("SELECT * FROM User")
    List<User> getAll ();

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllLiveData ();

}
