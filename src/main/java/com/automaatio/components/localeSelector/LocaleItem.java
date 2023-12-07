package com.automaatio.components.localeSelector;

import javafx.scene.image.Image;

/**
 * The {@code LocaleItem} class represents an item in a locale selector, typically
 * used in user interfaces for language selection. Each {@code LocaleItem} consists
 * of an image representing a flag and a string representing the language.
 *
 * This class provides a straightforward way to pair a visual representation (flag)
 * with a language identifier, facilitating easy and intuitive language selection in
 * applications.
 */
public class LocaleItem {
    private Image flag;
    private String language;

    /**
     * Constructs a new {@code LocaleItem} with the specified flag image and language string.
     *
     * @param flagi The image of the flag associated with the locale.
     * @param lang The language string representing the locale.
     */
    public LocaleItem(Image flagi, String lang){
        flag=flagi;
        language=lang;
    }

    /**
     * Gets the flag image of this locale item.
     *
     * @return The {@link Image} of the flag.
     */
    public Image getFlag() {
        return flag;
    }

    /**
     * Gets the language string of this locale item.
     *
     * @return The language string.
     */
    public String getLanguage(){
        return language;
    }

    /**
     * Returns a string representation of this locale item, which is the language string.
     *
     * @return The language string of this locale item.
     */
    @Override
    public String toString() {
        return language;
    }
}
