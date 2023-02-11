package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM AddProducts")
    List<AddProducts> getAllUsers();

    @Insert
    void insertUser(AddProducts... users);

    @Delete
    void delete(AddProducts user);
}
