package com.automaatio.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.automaatio.view.GraphicalUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The NavigationUtil class provides methods for navigating to different
 * parts of the application.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 * 8.9.2023
 */

public class NavigationUtil {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private FXMLLoader fxmlLoader;
    private final BundleLoader bundleLoader;
    private Parent root;

    /**
     * Class constructor
     */
    public NavigationUtil(){
        bundleLoader = new BundleLoader();
    }

    /**
     * Opens the main page of the application
     * @param event Mouse click
     * @throws IOException IOException thrown if the FXML Loader fails to load the FXML file
     */
    public void openMainPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/main-page.fxml"));
        setResourcesAndShow();
    }

    /**
     * Opens the login page
     * @param event Mouse click
     * @throws IOException IOException thrown if the FXML Loader fails to load the FXML file
     */
    public void openLoginPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        setResourcesAndShow();
    }

    /**
     * Reloads the page after a language change
     * @throws IOException IOException thrown if the FXML Loader fails to load the FXML file
     */
    public void changeLanguage() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/reloadAfterLangChange.fxml"));
        setResourcesAndShow();
    }

    /**
     * General method for setting up resources when opening any page the application.
     * Fetches the current locale from the cache, sets the resource bundle
     * accordingly, sets the CSS style sheet and orientation of UI elements (RTL/LTR).
     * Called by other methods in this class after loading the FXML file of a specific page.
     *
     * @throws IOException IOException thrown if the FXML Loader fails to load the FXML file
     */
    public void setResourcesAndShow() throws IOException {
        if (cache.getUser() != null) {
            fxmlLoader.setResources(bundleLoader.loadResourceByUsersLocale());
        } else {
            fxmlLoader.setResources(ResourceBundle.getBundle("TextResources", cache.getTempLocale()));
        }
        root = fxmlLoader.load();
        root.setNodeOrientation(cache.getDirection());
        Stage stage = GraphicalUI.getPrimaryStage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
