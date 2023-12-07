    package com.automaatio.controller.mainpage;

    import java.net.URL;
    import java.util.ResourceBundle;

    /**
     * The IController interface defines a contract for controllers in the main page of the application.
     * Controllers implementing this interface are expected to provide initialization logic for their respective components.
     *
     * @author Mikko Hänninen
     * @version 1.0
     */
    public interface IController {
        /**
         * Initializes the controller with the specified URL and ResourceBundle.
         * This method is called when the associated FXML file is loaded.
         *
         * @param location   The location used to resolve relative paths for the root object.
         * @param resources  The resources used to localize the root object, or null if the root object was not localized.
         */
        void initialize(URL location, ResourceBundle resources);
    }
