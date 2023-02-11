package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AddProducts {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "price")
    public String price;

    @ColumnInfo(name="image")
    public  String image;


    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
   // public SQLInvalidAuthorizationSpecException getImage() {
    //    return image;

}
