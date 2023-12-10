package com.automaatio.utils;

/**
 * The CountryNames enum is used to store and retrieve the names
 * of language options displayed in language selection dropdown menus.
 *
 * @author Mikko Hänninen, Nikita Nossenko, Elmo Erla, Matleena Kankaanpää
 * @version 1.0
 */

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

    /**
     * Returns the display name of a language
     * @return The display name of a language
     */
    @Override
    public String toString() {
        return name;
    }
}
