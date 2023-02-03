package com.example.api.ramsha.translator.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities={LanguageEntity.class},version=1)
public abstract class LanguageDBClass extends RoomDatabase {
    public abstract DAO LanguageDAO();
    private static LanguageDBClass database;
}
