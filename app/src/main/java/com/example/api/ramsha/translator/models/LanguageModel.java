package com.example.api.ramsha.translator.models;

public class LanguageModel {

    String languageCode;
    String languageName;
    String languageNameNative;
    String countryCode;

    public LanguageModel(String languageCode, String languageName, String languageNameNative, String countryCode) {
        this.languageCode = languageCode;
        this.languageName = languageName;
        this.languageNameNative = languageNameNative;
        this.countryCode = countryCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageNameNative() {
        return languageNameNative;
    }

    public void setLanguageNameNative(String languageNameNative) {
        this.languageNameNative = languageNameNative;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
