package com.example.greclamation.Room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Reclamation.class},version = 1)
public  abstract class ReclamationDatabase extends RoomDatabase {
    public abstract ReclamationDAO getDao();
    public static ReclamationDatabase INSTANCE;
    public static ReclamationDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,ReclamationDatabase.class,"reclamationDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()

                    .build();
        }
        return INSTANCE;
    }

}
