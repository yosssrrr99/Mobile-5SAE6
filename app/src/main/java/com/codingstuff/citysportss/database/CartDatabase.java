package com.codingstuff.citysportss.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codingstuff.citysportss.dao.CartDAO;
import com.codingstuff.citysportss.dao.ReclamationDAO;
import com.codingstuff.citysportss.utils.model.Reclamation;
import com.codingstuff.citysportss.utils.model.ShoeCart;

@Database(entities = {ShoeCart.class, Reclamation.class} , version = 1)

public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    public abstract ReclamationDAO reclamationDAO();
    private static CartDatabase instance;

    public static synchronized  CartDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , CartDatabase.class , "ShoeDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}
