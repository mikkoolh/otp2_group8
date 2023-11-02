package com.automaatio.utils;

public enum CountryNames {
    lang_US("English"),
    lang_FI("Suomi"),
    lang_RU("русский"),
    lang_RTL("RTL lang.")
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
