package com.automaatio.view;

import com.automaatio.Main;
import com.automaatio.controller.LoginController;
import com.automaatio.controller.MainPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class GraphicalUI extends Application {
    private static Stage primaryStage;
    private MainPageController mainC;
    private LoginController loginC;


    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/login.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        loginC = fxmlLoader.getController();
        loginC.setGraphicalUI(this);
        stage.setTitle("HomeAutomation v.2.11.-23");
        stage.setScene(scene);
        primaryStage = stage;
        stage.show();
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setMainC(MainPageController mainCont){
        System.out.println(mainCont);
        mainC = mainCont;
    }

    @Override
    public void stop() throws Exception {
        mainC.stopMoottori();
    }

    public static void main(String[] args) {
        launch();
    }

}