package com.automaatio.utils;

import java.io.IOException;
import java.util.ResourceBundle;

import com.automaatio.controller.MainPageController;
import com.automaatio.view.GraphicalUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
    private MainPageController mController;

    public NavigationUtil(){
        bundleLoader = new BundleLoader();
    }

    public void openMainPage(ActionEvent event, GraphicalUI graphUI) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/main-page.fxml"));
        root = fxmlLoader.load();
        graphUI.setMainC(fxmlLoader.getController());
        mController = fxmlLoader.getController();
        setResourcesAndShow();
    }

    public void openLoginPage(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        root = fxmlLoader.load();
        setResourcesAndShow();
    }

    public void changeLanguage() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/reloadAfterLangChange.fxml"));
        root = fxmlLoader.load();
        setResourcesAndShow();
    }

    public void setResourcesAndShow() throws IOException{
        if (cache.getUser() != null){
            fxmlLoader.setResources(bundleLoader.loadResourceByUsersLocale());
        } else {
            fxmlLoader.setResources(ResourceBundle.getBundle("TextResources", cache.getTempLocale()));
        }

        root.setNodeOrientation(cache.getDirection());
        Stage stage = GraphicalUI.getPrimaryStage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public MainPageController getMainPageController() {
        return mController;
    }
}
