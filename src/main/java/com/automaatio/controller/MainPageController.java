package com.automaatio.controller;


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
 * @author Mikko Hänninen 19.9.2023
 *
 */

public class MainPageController implements Initializable {
    @FXML
    private Pane mainPane, menuPane;
    private CacheSingleton cache = CacheSingleton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cache.setMenuPane(menuPane);
        cache.setMainPane(mainPane);
        setMainPane();
        setMenuPane();
    }


    public void setMenuPane(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-menu.fxml"));
            Parent firstView = loader.load();
            menuPane.getChildren().add(firstView);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void setMainPane(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/welcome.fxml"));
            Parent firstView = loader.load();
            mainPane.getChildren().add(firstView);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
