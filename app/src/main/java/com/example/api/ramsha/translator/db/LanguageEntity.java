package com.example.api.ramsha.translator.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LanguageEntity {
    public LanguageEntity(){
    }
    @PrimaryKey(autoGenerate = true)
    public int ID;
    @ColumnInfo(name="Input_Word")
    public String inputWord;
    @ColumnInfo(name="Output_Word")
    public String outputWord;
    @ColumnInfo(name="Input_Language_Code")
    public String inputLanguageCode;
    @ColumnInfo(name="Input_Language_Name")
    public String inputLanguageName;
    @ColumnInfo(name="Translation_Language_Code")
    public String translationLanguageCode;
    @ColumnInfo(name="Translation_Language_Name")
    public String translationLanguageName;
    @ColumnInfo(name="Is_Favourite")
    public Boolean isFavourite;

    public String getInputWord() {
        return inputWord;
    }

    public void setInputWord(String inputWord) {
        this.inputWord = inputWord;
    }

    public String getOutputWord() {
        return outputWord;
    }

    public void setOutputWord(String outputWord) {
        this.outputWord = outputWord;
    }

    public String getInputLanguageCode() {
        return inputLanguageCode;
    }

    public void setInputLanguageCode(String inputLanguageCode) {
        this.inputLanguageCode = inputLanguageCode;
    }

    public String getInputLanguageName() {
        return inputLanguageName;
    }

    public void setInputLanguageName(String inputLanguageName) {
        this.inputLanguageName = inputLanguageName;
    }

    public String getTranslationLanguageCode() {
        return translationLanguageCode;
    }

    public void setTranslationLanguageCode(String translationLanguageCode) {
        this.translationLanguageCode = translationLanguageCode;
    }

    public String getTranslationLanguageName() {
        return translationLanguageName;
    }

    public void setTranslationLanguageName(String translationLanguageName) {
        this.translationLanguageName = translationLanguageName;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}
