package com.automaatio.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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


    /**
     *
     * @param event
     * @throws IOException
     */
    public void openMainPage(ActionEvent event) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/main-page.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the login page
     * @param event Button click
     * @throws IOException
     */
    public void openLoginPage(ActionEvent event) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
