package com.codingstuff.citysportss.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.codingstuff.citysportss.utils.model.Reclamation;

import java.util.List;
@Dao
public interface ReclamationDAO {

    @Insert
    public void InsertRec(Reclamation reclamation);

    @Query("SELECT * FROM reclamation_tab")
    List<Reclamation>getAllReclamation();


}
