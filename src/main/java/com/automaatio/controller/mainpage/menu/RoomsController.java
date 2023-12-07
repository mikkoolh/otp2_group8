package com.automaatio.controller.mainpage.menu;

import com.automaatio.components.CreateVBoxColumn;
import com.automaatio.controller.mainpage.clickActions.DeviceGroupsClick;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The RoomsController class is responsible for managing the "Rooms" menu in the HomeAutomation application's main page.
 * It implements the Initializable interface to handle initialization of the controller and the Menu interface to define menu-related functionality.
 * The controller is associated with an FXML file representing the UI for managing rooms.
 * Users can view existing rooms, add new rooms, and interact with room-related functionalities.
 * The class utilizes the CreateVBoxColumn component for dynamically creating and managing VBox columns.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class RoomsController implements Initializable, Menu {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    @FXML
    private TextField newRoomTextField;
    private final CreateVBoxColumn deviceGroupRow = new CreateVBoxColumn();

    @FXML
    private VBox roomsVBox;

    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();

    /**
     * Initializes the controller and calls the show() method to populate the VBox with room rows.
     *
     * @param location  The URL location of the FXML file.
     * @param resources The ResourceBundle containing localized resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
    }

    /**
     * Populates the VBox with room rows fetched from the database.
     */
    public void show() {
        roomsVBox.getChildren().clear();
        List<DeviceGroup> deviceGroups = deviceGroupDAO.getRoomsByUser(cache.getUser());
        for (DeviceGroup deviceGroup : deviceGroups) {
            roomsVBox.getChildren().add(deviceGroupRow.create(deviceGroup, roomsVBox, new DeviceGroupsClick()));
        }
    }

    /**
     * Handles the click action when the "Add Room" button is clicked.
     * Creates a new room with the specified name, adds it to the database, and updates the UI to reflect the changes.
     */
    @FXML
    private void onAddGroupClick() {
        String deviceGroupName = newRoomTextField.getText();
        if (deviceGroupName == null || deviceGroupName.trim().isEmpty()) {
            System.out.println("virhe nimeä antaessa");
            return;
        }
        DeviceGroup newRoom = new DeviceGroup(newRoomTextField.getText(), cache.getUser());
        deviceGroupDAO.addObject(newRoom);
        newRoomTextField.clear();
        System.out.println(newRoom);
        System.out.println("lisätty " + newRoom.getName() + " tietokantaan");
        show();
    }
}