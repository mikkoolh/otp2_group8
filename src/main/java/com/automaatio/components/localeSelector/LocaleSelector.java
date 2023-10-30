package com.automaatio.components.localeSelector;


import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class LocaleSelector {
    private ComboBox<LocaleItem> languagesCBox;
    private Image us, fi;
    private final String lang_US = "English", lang_FI= "Suomi";
    private final int SIZE = 20;
    private final String pathUSFlag = "/images/263-united-states-of-america.png", pathFIFlag = "/images/125-finland.png";

    public LocaleSelector(){
        languagesCBox = new ComboBox<>();
        us = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathUSFlag)));
        fi = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathFIFlag)));
    }

    public ComboBox getLocaleSelector(){

        List<LocaleItem> localesList = getLocalesList();
        languagesCBox.setItems(FXCollections.observableArrayList(localesList));
        setCellFactory();


        return languagesCBox;
    }



    private List<LocaleItem> getLocalesList(){
        List<LocaleItem> temp = new ArrayList<>();
        temp.add(new LocaleItem(us, lang_US));
        temp.add(new LocaleItem(fi, lang_FI));
        return temp;
    }

    private void setCellFactory(){
        languagesCBox.setCellFactory(param -> new ListCell<LocaleItem>() {
            private final Label label = new Label();
            private final ImageView imageView = new ImageView();

            protected void updateItem(LocaleItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item.getLanguage());
                    imageView.setImage(item.getFlag());
                    imageView.setFitHeight(SIZE);
                    imageView.setFitWidth(SIZE);
                    setGraphic(new HBox(5, imageView, label));
                }
            }
        });
    }
}
