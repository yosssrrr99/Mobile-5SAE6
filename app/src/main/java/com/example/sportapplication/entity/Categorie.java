package com.example.sportapplication.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categorie {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

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
}
