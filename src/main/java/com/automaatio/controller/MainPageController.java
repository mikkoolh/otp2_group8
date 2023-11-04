package com.automaatio.controller;


import com.automaatio.model.Moottori;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.view.GraphicalUI;
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
 * Controller for the app dashboard
 * @author Mikko Hänninen 19.9.2023
 *
 */

public class MainPageController extends Thread implements Initializable{
    @FXML
    private Pane mainPane, menuPane;
    private CacheSingleton cache = CacheSingleton.getInstance();
    private BundleLoader bundleLoader = new BundleLoader();
    private Moottori moottori;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResourceBundle resourceBundle = resources;
        cache.setMenuPane(menuPane);
        cache.setMainPane(mainPane);
        setMainPane(resourceBundle);
        setMenuPane(resourceBundle);
        moottori = new Moottori(this);
        moottori.start();
        GraphicalUI.setMainC(this);
    }

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

    public void stopMoottori(){
        System.out.println("window closed");
        moottori.stopRunning();
    }
}
