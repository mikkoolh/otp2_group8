package com.automaatio.utils;

public enum CountryNames {
    lang_US("English"),
    lang_FI("Suomi"),
    lang_RU("Русский"),
    lang_RTL("RTL lang."),
    lang_SA("اَلْعَرَبِيَّةُ")
    ;

    private String name;
    CountryNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
