package com.automaatio.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class GraphicalUI extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/login.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HomeAutomation v.2.11.-23");
        stage.setScene(scene);
        primaryStage = stage;
        stage.show();
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }

}