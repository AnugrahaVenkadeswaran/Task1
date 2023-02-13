package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM AddProducts")
    List<AddProducts> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(AddProducts users);
    @Update
    void updateUsers(AddProducts users);

    @Delete
    void delete(AddProducts user);
}
