package com.example.sportapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sportapplication.entity.Produit;

import java.util.List;


@Dao
public interface ProduitDAO {

    @Insert
    void insert(Produit produit);

    @Update
    void update(Produit produit);

  @Query("delete from produit where id=:id")
    void delete(int id);

  @Query("Select * from produit")
  List<Produit> getAllProduit();


}
