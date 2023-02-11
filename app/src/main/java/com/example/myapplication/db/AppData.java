package com.example.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities= {AddProducts.class},version  =1)
public abstract class  AppData extends RoomDatabase {
    public abstract UserDAO userDAO();

   private static AppData INSTANCE;

   public static AppData getDBInstance(Context context){
       if(INSTANCE == null) {
           INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppData.class, "DB_NAME")
                   .allowMainThreadQueries()
                   .build();

       }
       return INSTANCE;
   }


}



