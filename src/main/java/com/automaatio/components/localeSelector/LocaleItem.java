package com.automaatio.components.localeSelector;

import com.automaatio.utils.ViewDirection;
import javafx.scene.image.Image;

public class LocaleItem {
    private Image flag;
    private String language;

    public LocaleItem(Image flagi, String lang){
        flag=flagi;
        language=lang;
    }
    public Image getFlag() {
        return flag;
    }
    public String getLanguage(){
        return language;
    }

    @Override
    public String toString() {
        return language;
    }
}
