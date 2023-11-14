package com.example.greclamation.Room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReclamationDAO {

    @Insert
    void InsertReclamation(Reclamation reclamation);

    @Update
    void updatereclamation(Reclamation reclamation);

    @Query("DELETE FROM reclamation_tab WHERE id=:id")
    void delete(int id );


    @Query("SELECT * FROM reclamation_tab")
    List<Reclamation>getAllReclamation();

}
