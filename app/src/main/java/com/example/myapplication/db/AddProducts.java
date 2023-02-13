package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AddProducts {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "price")
    public String price;


    public AddProducts(int id, String title, String price) {
        this.id = id;
        this.title = title;
        this.price = price;
       // this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
