package com.automaatio.components.localeSelector;


import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.CountryNames;
import com.automaatio.utils.NavigationUtil;
import com.automaatio.utils.ViewDirection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class LocaleSelector {
    private ComboBox<LocaleItem> languagesCBox;
    private Image us, fi, ru, un;
    private final String lang_US = CountryNames.lang_US.toString(), lang_FI= CountryNames.lang_FI.toString(), lang_RTL = CountryNames.lang_RTL.toString(),  lang_RU = CountryNames.lang_RU.toString();
    private NavigationUtil navigationUtil = new NavigationUtil();
    private Locale userLocale;
    private final int SIZE = 30, idx_FI = 0, idx_US = 1, idx_RU = 2, idx_RTL = 3;
    private final UserDAO userDAO = new UserDAO();
    private CacheSingleton cacheSingleton = CacheSingleton.getInstance();
    private final String pathUSFlag = "/images/263-united-states-of-america.png", pathFIFlag = "/images/125-finland.png", pathRUFlag="/images/248-russia.png", pathUNFlag = "/images/082-united-nations.png";

    public LocaleSelector(){
        languagesCBox = new ComboBox<LocaleItem>();
        fetchImages();
        fetchLocale(cacheSingleton.getUser().getUsername());
    }

    public ComboBox<LocaleItem> getComboBox(){

        List<LocaleItem> localesList = getLocalesList();
        languagesCBox.setItems(FXCollections.observableArrayList(localesList));
        setInitialValue();
        setCellFactory();
        languagesCBox.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends LocaleItem> observable, LocaleItem oldValue, LocaleItem newValue) {
                String username = cacheSingleton.getUser().getUsername(), value = newValue.toString();
                cacheSingleton.setDirection(ViewDirection.LTR);
                if(value.equals(lang_FI)){
                    userDAO.updateLocale(username, new Locale("fi", "FI"));
                    showNewLang();
                } else if (value.equals(lang_US)){
                    userDAO.updateLocale(username, new Locale("en", "US"));
                    showNewLang();
                } else if (value.equals(lang_RU)) {
                    userDAO.updateLocale(username, new Locale("ru", "RU"));
                    showNewLang();
                } else if (value.equals(CountryNames.lang_RTL.toString())) {
                    cacheSingleton.setDirection(ViewDirection.RTL);

                    //TODO KORJAA LOCALE KUN PÄÄTETTY!!!

                    userDAO.updateLocale(username, new Locale("fi", "FI"));
                    showNewLang();
                } else {
                    System.out.println("Invalid click");
                }
            }
        });
        languagesCBox.setPrefWidth(286);
        languagesCBox.setPrefHeight(48);
        languagesCBox.getStyleClass().add("input-field");

        return languagesCBox;
    }

    private void setInitialValue() {
        List<LocaleItem> temp = getLocalesList();
        System.out.println(userLocale +" at set initial");
        if(userLocale.toString().equals("fi_FI")){
            languagesCBox.setValue(temp.get(idx_FI));
        } else if (userLocale.toString().equals("en_US")) {
            languagesCBox.setValue(temp.get(idx_US));
        } else if (userLocale.toString().equals("ru_RU")) {
            languagesCBox.setValue(temp.get(idx_RU));
        } else if (userLocale.toString().equals(lang_RTL)) { //TODO KORJAAA KUN VALITTU
            languagesCBox.setValue(temp.get(idx_RTL));
        } else {
            System.out.println("not valid initial value");
        }

    }


    private List<LocaleItem> getLocalesList(){
        List<LocaleItem> temp = new ArrayList<>();
        temp.add(idx_FI, new LocaleItem(fi, lang_FI));
        temp.add(idx_US, new LocaleItem(us, lang_US));
        temp.add(idx_RU, new LocaleItem(ru, lang_RU));
        temp.add(idx_RTL, new LocaleItem(un, lang_RTL));
        return temp;
    }

    private void setCellFactory(){
        languagesCBox.setCellFactory(param -> new ListCell<LocaleItem>() {
            private final Label label = new Label();
            private final ImageView imageView = new ImageView();

            @Override
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

    private void fetchImages() {
        us = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathUSFlag)));
        fi = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathFIFlag)));
        ru = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathRUFlag)));
        un = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathUNFlag)));
    }

    private void fetchLocale(String username) {
        userLocale = userDAO.getLocale(username);
        System.out.println(userLocale);
    }
    private void showNewLang(){
        try {
            navigationUtil.changeLanguage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
