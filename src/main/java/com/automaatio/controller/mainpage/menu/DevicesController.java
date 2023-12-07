package com.automaatio.controller.mainpage.menu;

import com.automaatio.components.CreateVBoxColumn;
import com.automaatio.controller.mainpage.clickActions.DevicesClick;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The DevicesController class is responsible for managing the "Devices" menu in the HomeAutomation application's main page.
 * It implements the Initializable interface to handle initialization and the Menu interface to define menu-related functionality.
 * The class is associated with an FXML file representing the UI for managing devices.
 * It allows users to view, add, and interact with devices in the system.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class DevicesController implements Initializable, Menu {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private final CreateVBoxColumn deviceRow = new CreateVBoxColumn();
    DeviceDAO deviceDAO = new DeviceDAO();

    @FXML
    private TextField deviceNameField;
    @FXML
    private VBox devicesVBox;

    /**
     * Initializes the controller and calls the show() method to populate the VBox with device rows.
     *
     * @param location  The URL location of the FXML file.
     * @param resources The ResourceBundle containing localized resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
    }

    /**
     * Populates the VBox with device rows fetched from the database.
     */
    public void show() {
        devicesVBox.getChildren().clear();
        List<Device> devices = deviceDAO.getDevicesByUserName(cache.getUser().getUsername());
        for (Device device : devices) {
            devicesVBox.getChildren().add(deviceRow.create(device, devicesVBox, new DevicesClick()));
        }
    }

    /**
     * Handles the "Add" button action by creating a new device with the specified name, adding it to the database,
     * and updating the UI to reflect the changes.
     */
    @FXML
    public void add() {
        String deviceName = deviceNameField.getText();
        if (deviceName == null || deviceName.trim().isEmpty()) {
            System.out.println("virhe nimeä antaessa");
            return;
        }

        Device device = new Device(0, deviceName, null, null, cache.getUser().getUsername());

        deviceDAO.addObject(device);
        System.out.println(device);
        deviceNameField.clear();
        System.out.println(device.getName() + ", id: " + device.getDeviceID());
        show();
    }
}
