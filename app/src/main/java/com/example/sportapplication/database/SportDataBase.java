package com.example.sportapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.sportapplication.dao.CategorieDAO;
import com.example.sportapplication.dao.ProduitDAO;
import com.example.sportapplication.entity.Categorie;
import com.example.sportapplication.entity.Produit;
import com.example.sportapplication.entity.SizeListConverter;

@Database(entities = {Categorie.class, Produit.class}, version = 2, exportSchema = false)
@TypeConverters({SizeListConverter.class, /* other converters if any */})
public abstract class SportDataBase extends RoomDatabase {
    public abstract CategorieDAO categorieDAO();
    public abstract ProduitDAO produitDAO();
    private static volatile SportDataBase INSTANCE;

    public static SportDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SportDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SportDataBase.class, "sport_database")
                            .allowMainThreadQueries()

                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
