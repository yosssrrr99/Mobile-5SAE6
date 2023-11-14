package com.codingstuff.citysportss.utils.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reclamation_tab")
public class Reclamation {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String subject;
    private String description;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reclamation(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }
}
