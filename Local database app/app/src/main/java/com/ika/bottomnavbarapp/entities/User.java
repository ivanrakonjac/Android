package com.ika.bottomnavbarapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User{

    @PrimaryKey (autoGenerate = true)
    private long id;

    private String username;
    private String password;
    private String firsName;
    private String lastName;
    private int ages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAges() {
        return ages;
    }

    public void setAges(int ages) {
        this.ages = ages;
    }
}
