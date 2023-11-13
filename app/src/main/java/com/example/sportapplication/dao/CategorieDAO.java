package com.example.sportapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sportapplication.entity.Categorie;

import java.util.List;

@Dao
public interface CategorieDAO {

    @Insert
    void insert(Categorie categorie);

    @Update
    void update(Categorie categorie);

    @Query("delete from categorie where id=:id")
    void delete(int id);

    @Query("Select * from categorie")
    List<Categorie> getAllCategorie();

}
