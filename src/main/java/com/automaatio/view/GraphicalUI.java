package com.automaatio.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The GraphicalUI class is the main entry point for the HOME//KOTI application's graphical user interface.
 * It extends the JavaFX Application class and initializes the primary stage with the login scene.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class GraphicalUI extends Application {
    /**
     * The primary stage for the GUI.
     */
    private static Stage primaryStage;
    /**
     * The start method is called to start the JavaFX application. It initializes the primary stage with the login scene.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
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
    /**
     * Gets the primary stage of the application.
     * @return The primary stage.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }

}