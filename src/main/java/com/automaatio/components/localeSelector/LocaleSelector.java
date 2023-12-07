package com.automaatio.components.localeSelector;

import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.CountryNames;
import com.automaatio.utils.NavigationUtil;
import com.automaatio.view.GraphicalUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;

/**
 * The {@code LocaleSelector} class provides functionality to create and manage a
 * ComboBox for selecting locales. It is designed to allow users to switch between
 * different languages and regions, represented by flags and language names.
 * <p>
 * This class handles the creation of the ComboBox, populating it with {@link LocaleItem}
 * objects, and managing the actions to be taken when a new locale is selected. It also
 * supports updating the user interface and user preferences based on the selected locale.
 * </p>
 *
 * @version 1.0
 * @since 7.10.2023
 */
public class LocaleSelector {
    private ComboBox<LocaleItem> languagesCBox;
    private Image us, fi, ru, sa;
    private final String lang_US = CountryNames.lang_US.toString(), lang_FI = CountryNames.lang_FI.toString(), lang_RU = CountryNames.lang_RU.toString(), lang_SA = CountryNames.lang_SA.toString();
    private NavigationUtil navigationUtil = new NavigationUtil();
    private Locale userLocale;
    private final int SIZE = 30, idx_FI = 0, idx_US = 1, idx_RU = 2, idx_SA = 3;
    private final UserDAO userDAO = new UserDAO();
    private CacheSingleton cache = CacheSingleton.getInstance();
    private final boolean loggedIn = cache.getUser() != null;
    private final String pathUSFlag = "/images/263-united-states-of-america.png", pathFIFlag = "/images/125-finland.png", pathRUFlag = "/images/248-russia.png", pathSAFlag = "/images/133-saudi-arabia.png";

    /**
     * Constructs a {@code LocaleSelector} and initializes its components.
     * It fetches images for the flags, sets the initial locale based on user
     * preferences or a temporary cache, and configures the ComboBox.
     */
    public LocaleSelector() {
        languagesCBox = new ComboBox<LocaleItem>();
        languagesCBox.setId("languageBox");
        fetchImages();
        if (loggedIn) {
            fetchLocale(cache.getUser().getUsername());
        } else {
            userLocale = cache.getTempLocale();
        }
    }

    /**
     * Gets the ComboBox configured with locale options.
     *
     * @return The configured {@link ComboBox} of {@link LocaleItem}.
     */
    public ComboBox<LocaleItem> getComboBox() {

        List<LocaleItem> localesList = getLocalesList();
        languagesCBox.setItems(FXCollections.observableArrayList(localesList));
        setInitialValue();
        setCellFactory();
        languagesCBox.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends LocaleItem> observable, LocaleItem oldValue, LocaleItem newValue) {
                String value = newValue.toString(), username = "";
                if (loggedIn) {
                    username = cache.getUser().getUsername();
                }
                cache.setDirection(NodeOrientation.LEFT_TO_RIGHT);
                Locale tempLocale = null;
                if (value.equals(lang_FI)) {
                    tempLocale = new Locale("fi", "FI");
                } else if (value.equals(lang_US)) {
                    tempLocale = new Locale("en", "US");
                } else if (value.equals(lang_RU)) {
                    tempLocale = new Locale("ru", "RU");
                } else if (value.equals(lang_SA)) {
                    cache.setDirection(NodeOrientation.RIGHT_TO_LEFT);
                    tempLocale = new Locale("ar", "SA");
                } else {
                    System.out.println("Invalid click");
                }
                if (loggedIn) {
                    userDAO.updateLocale(username, tempLocale);
                } else {
                    cache.setTempLocale(tempLocale);
                }
                try {
                    showNewLang();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        languagesCBox.setPrefWidth(286);
        languagesCBox.setPrefHeight(48);
        languagesCBox.getStyleClass().add("input-field");

        return languagesCBox;
    }

    /**
     * Fetches and sets the initial value of the ComboBox based on the user's locale.
     */
    private void setInitialValue() {
        List<LocaleItem> temp = getLocalesList();
        if (userLocale.toString().equals("fi_FI")) {
            languagesCBox.setValue(temp.get(idx_FI));
        } else if (userLocale.toString().equals("en_US")) {
            languagesCBox.setValue(temp.get(idx_US));
        } else if (userLocale.toString().equals("ru_RU")) {
            languagesCBox.setValue(temp.get(idx_RU));
        } else if (userLocale.toString().equals("ar_SA")) {
            languagesCBox.setValue(temp.get(idx_SA));
        } else {
            System.out.println("not valid initial value");
        }
    }

    /**
     * Generates a list of {@link LocaleItem} objects representing available locales.
     *
     * @return A list of {@link LocaleItem} objects.
     */
    private List<LocaleItem> getLocalesList() {
        List<LocaleItem> temp = new ArrayList<>();
        temp.add(idx_FI, new LocaleItem(fi, lang_FI));
        temp.add(idx_US, new LocaleItem(us, lang_US));
        temp.add(idx_RU, new LocaleItem(ru, lang_RU));
        temp.add(idx_SA, new LocaleItem(sa, lang_SA));
        return temp;
    }

    /**
     * Sets the cell factory for the ComboBox to customize the display of items.
     */
    private void setCellFactory() {
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

    /**
     * Fetches images for the flags used in the locale selection.
     */
    private void fetchImages() {
        us = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathUSFlag)));
        fi = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathFIFlag)));
        ru = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathRUFlag)));
        sa = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathSAFlag)));
    }

    /**
     * Fetches the user's locale from the database based on the username.
     *
     * @param username The username of the user.
     */
    private void fetchLocale(String username) {
        userLocale = userDAO.getLocale(username);
        System.out.println(userLocale);
    }

    /**
     * Updates the user interface to reflect the newly selected language.
     *
     * @throws IOException If there is an error loading the new language resources.
     */
    private void showNewLang() throws IOException {
        if (loggedIn) {
            try {
                navigationUtil.changeLanguage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            FXMLLoader fxmlLoader = cache.getCurrentLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle("TextResources", cache.getTempLocale()));
            System.out.println(cache.getTempLocale() + " at showNewLang");
            Parent root = null;
            root = fxmlLoader.load();
            root.setNodeOrientation(cache.getDirection());
            Stage stage = GraphicalUI.getPrimaryStage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }
}
