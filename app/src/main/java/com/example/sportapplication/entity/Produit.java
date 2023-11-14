package com.example.sportapplication.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity
public class Produit implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String price;
    private String des;

    public List<Size> getSizes() {
        return sizes;
    }



    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    @TypeConverters(SizeListConverter.class)
    private List<Size> sizes;

    String type;


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    String rating;

    public Produit() {
    }

    public Produit(int id, String name, String price, String des, String type, List<Size> sizes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.des = des;
        this.type = type;
        this.sizes = sizes;
    }



    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] imageData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}
