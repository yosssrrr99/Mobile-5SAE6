package com.example.sportapplication.adapter;

import com.example.sportapplication.entity.Produit;

public interface ApdateListener {

    void OnUpdate(Produit produit);
    void OnDelete(int id,int pos);

}
