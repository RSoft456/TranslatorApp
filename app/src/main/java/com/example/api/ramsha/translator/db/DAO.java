package com.example.api.ramsha.translator.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {

    //Insert Querry
    @Insert
    void InsertTranslation(LanguageEntity Student);

    @Query("SELECT * FROM LanguageEntity")
    List<LanguageEntity> getall();
}
