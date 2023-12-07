package com.automaatio.controller;


import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.util.Locale;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for the application dashboard.
 * Manages the initialization of the main and menu panes, setting up the user interface.
 *
 * @author Mikko Hänninen
 * @since 19.9.2023
 */
public class MainPageController implements Initializable {
    @FXML
    private Pane mainPane, menuPane;
    private CacheSingleton cache = CacheSingleton.getInstance();
    private BundleLoader bundleLoader = new BundleLoader();
    /**
     * Initializes the controller after FXML loading.
     * Sets up the main and menu panes with the appropriate content based on the user's locale.
     *
     * @param location The URL location of the FXML file.
     * @param resources The ResourceBundle containing localized resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResourceBundle resourceBundle = resources;
        cache.setMenuPane(menuPane);
        cache.setMainPane(mainPane);
        setMainPane(resourceBundle);
        setMenuPane(resourceBundle);
    }
    /**
     * Sets up the menu pane with content loaded from the "main-menu.fxml" file.
     *
     * @param bundle The ResourceBundle containing localized resources.
     */
    public void setMenuPane(ResourceBundle bundle){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-menu.fxml"));
            loader.setResources(bundleLoader.loadResourceByUsersLocale());
            Parent firstView = loader.load();
            menuPane.getChildren().add(firstView);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**
     * Sets up the main pane with content loaded from the "welcome.fxml" file.
     *
     * @param bundle The ResourceBundle containing localized resources.
     */
    public void setMainPane(ResourceBundle bundle){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/welcome.fxml"));
            loader.setResources(bundleLoader.loadResourceByUsersLocale());
            Parent firstView = loader.load();
            mainPane.getChildren().add(firstView);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
