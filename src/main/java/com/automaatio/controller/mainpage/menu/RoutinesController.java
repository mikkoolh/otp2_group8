package com.automaatio.controller.mainpage.menu;

import com.automaatio.components.CreateVBoxColumn;
import com.automaatio.controller.mainpage.clickActions.RoutinesClick;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The RoutinesController class is responsible for managing the "Routines" menu in the HomeAutomation application's main page.
 * It implements the Initializable interface to handle initialization of the controller.
 * The controller is associated with an FXML file representing the UI for managing automation routines.
 * Users can view existing routines and interact with routine-related functionalities.
 * The class utilizes the CreateVBoxColumn component for dynamically creating and managing VBox columns.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class RoutinesController implements Initializable {

    private final CacheSingleton cache = CacheSingleton.getInstance();

    private final CreateVBoxColumn deviceRow = new CreateVBoxColumn();

    @FXML
    private VBox routinesVBox;

    /**
     * Populates the VBox with routine rows fetched from the database.
     */
    public void showRoutines() {
        routinesVBox.getChildren().clear();
        DeviceDAO deviceDAO = new DeviceDAO();
        List<Device> devices = deviceDAO.getAutoDevices();

        // Testejä varten
        //List<Device> devices = deviceDAO.getAll();

        for (Device device : devices) {
            routinesVBox.getChildren().add(deviceRow.create(device, routinesVBox, new RoutinesClick()));
        }
    }

    /**
     * Initializes the controller and calls the showRoutines() method to populate the VBox with routine rows.
     *
     * @param location  The URL location of the FXML file.
     * @param resources The ResourceBundle containing localized resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showRoutines();
    }
}