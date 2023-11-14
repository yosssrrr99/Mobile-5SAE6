package com.example.greclamation.Room;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reclamation_tab")
public class Reclamation {
    @PrimaryKey(autoGenerate = true)
    private int id ;

    private String Sujet;

    private String description;

    public Reclamation(int id, String sujet, String description) {
        this.id = id;
        Sujet = sujet;
        this.description = description;
    }

    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return Sujet;
    }

    public void setSujet(String sujet) {
        Sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
