package com.example.sportapplication.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categorie {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setName(String name) {
        this.name = name;
    }
}
