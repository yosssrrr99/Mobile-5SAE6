package com.example.gestionusermobile.Entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    //@Query("Select * from User where id=:id")
    //hamedvoid delete(int id);

    @Query("Select * from User")
    List<User> getAllUsers();
}
