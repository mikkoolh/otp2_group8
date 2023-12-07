package com.automaatio.controller.mainpage;


import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for the app dashboard
 * @author Mikko Hänninen
 * @version 1.0
 * @since 19.9.2023
 */

public class LanguageReloadController implements Initializable {
    @FXML
    private Pane mainPane, menuPane;
    private CacheSingleton cache = CacheSingleton.getInstance();
    private BundleLoader bundleLoader = new BundleLoader();
    /**
     * Initializes the controller with the specified URL and ResourceBundle.
     * This method is called when the associated FXML file is loaded.
     *
     * @param location   The location used to resolve relative paths for the root object.
     * @param resources  The resources used to localize the root object, or null if the root object was not localized.
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
     * Sets up the menu pane with language-specific content.
     *
     * @param bundle The ResourceBundle containing language-specific resources.
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
     * Sets up the main pane with language-specific content.
     *
     * @param bundle The ResourceBundle containing language-specific resources.
     */
    public void setMainPane(ResourceBundle bundle){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user-profile.fxml"));
            loader.setResources(bundleLoader.loadResourceByUsersLocale());
            Parent firstView = loader.load();
            mainPane.getChildren().add(firstView);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
