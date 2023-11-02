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
 * Functions for navigating the app
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class NavigationUtil {
    private CacheSingleton cache = CacheSingleton.getInstance();
    private FXMLLoader fxmlLoader;
    private BundleLoader bundleLoader;
    private Parent root;

    public NavigationUtil(){
        bundleLoader = new BundleLoader();
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    public void openMainPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/main-page.fxml"));
        setResourcesAndShow();
    }

    /**
     * Opens the login page
     * @param event Button click
     * @throws IOException
     */
    public void openLoginPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        setResourcesAndShow();
    }

    public void changeLanguage() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/reloadAfterLangChange.fxml"));
        setResourcesAndShow();
    }

    public void setResourcesAndShow() throws IOException{
        fxmlLoader.setResources(bundleLoader.loadResourceByUsersLocale());
        root = fxmlLoader.load();
        setRootDirection(); //Set RTL or LTR
        Stage stage = GraphicalUI.getPrimaryStage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void setRootDirection(){
        if(cache.getDirection() == ViewDirection.RTL){
            root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else{
            root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }
}
