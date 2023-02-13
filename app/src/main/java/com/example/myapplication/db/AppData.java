package com.example.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AddProducts.class}, version = 1)
public abstract class AppData extends RoomDatabase {
    public abstract UserDAO userDao();
}









