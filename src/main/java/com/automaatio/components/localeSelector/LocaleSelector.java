package com.automaatio.components.localeSelector;


import com.automaatio.model.database.UserDAO;
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
    private Locale userLocale;
    private final int SIZE = 30;
    private final UserDAO userDAO = new UserDAO();
    private final String pathUSFlag = "/images/263-united-states-of-america.png", pathFIFlag = "/images/125-finland.png";

    public LocaleSelector(){
        languagesCBox = new ComboBox<LocaleItem>();
        us = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathUSFlag)));
        fi = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathFIFlag)));
        fetchLocale("mikko");
    }

    public ComboBox<LocaleItem> getComboBox(){

        List<LocaleItem> localesList = getLocalesList();
        languagesCBox.setItems(FXCollections.observableArrayList(localesList));
        setCellFactory();
        languagesCBox.setPrefWidth(286);
        languagesCBox.setPrefHeight(48);
        languagesCBox.getStyleClass().add("input-field");


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

            @Override
            protected void updateItem(LocaleItem item, boolean empty) {
                super.updateItem(item, empty);

                if(userLocale.toString().equals("fi_FI")){

                } else if (userLocale.toString().equals("en_US")) {
                    
                }

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
    private void fetchLocale(String username){
        userLocale = userDAO.getLocale(username);
        System.out.println(userLocale);
    }
}
