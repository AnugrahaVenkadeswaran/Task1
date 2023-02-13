package com.example.myapplication;

import java.io.Serializable;

public class products implements Serializable {
    private String title;
    private String price;
    private String category;
    private String image;

    private String description;

    public products(String title,String price,String category,String image,String description) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.image = image;
        this.description=description;
    }

    public products() {
        this.title = title;
        this.price = price;
        this.category = category;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
  /*  private String id;
    private String title;
    private String price;
    private String description;
    private String category;
    private String image;



    public products(String title, String price, String category, String image) {
        this.title=title;
        this.price=price;
        this.category=category;
        this.image=image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public products(String id, String title, String price,String description, String image, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }*/
}
