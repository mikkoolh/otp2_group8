package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for managing devices in the main page.
 * This class implements the Initializable and IController interfaces.
 *
 * @author Elmo Erla
 * @version 1.0
 */
public class DeviceController implements Initializable, IController {

    DeviceDAO deviceDAO = new DeviceDAO();
    DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();

    private final CacheSingleton cache = CacheSingleton.getInstance();

    @FXML
    private TextField deviceNameField;
    @FXML
    private Button changeNameButton;
    @FXML
    private TextField modelCode;
    @FXML
    private ComboBox<DeviceGroup> deviceGroup;
    @FXML
    private TextField usageData;
    @FXML
    private Button changeAutomationButton;

    private Device device = CacheSingleton.getInstance().getDevice();
    /**
     * Default constructor for DeviceController.
     */
    public DeviceController() {}
    /**
     * Initializes the controller.
     *
     * @param location   The location used to resolve relative paths for the root object.
     * @param resources  The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceNameField.setText(device.getName());
        changeNameButton.setOnAction(e -> changeDeviceName());
        changeAutomationButton.setOnAction((e -> changeAutomation()));

        modelCode.setText(device.getModelCode());
        usageData.setText(Long.toString(device.getUsageData()));

        DeviceGroup currentDeviceGroup = device.getDeviceGroup();
        if (currentDeviceGroup != null) {
            deviceGroup.setPromptText(currentDeviceGroup.getName());
        }

        populateDeviceGroupComboBox();

        deviceGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deviceGroup.setPromptText(newValue.getName());

                int deviceId = device.getDeviceID();
                int newDeviceGroupId = newValue.getDeviceGroupId();

                deviceDAO.updateDeviceGroup(deviceId, newDeviceGroupId);
            }
        });
    }
    /**
     * Populates the device group ComboBox with rooms retrieved from the database.
     */
    private void populateDeviceGroupComboBox() {
        List<DeviceGroup> rooms = deviceGroupDAO.getRoomsByUser(cache.getUser());
        deviceGroup.getItems().addAll(rooms);
        deviceGroup.setCellFactory((comboBox) -> {
            return new ListCell<DeviceGroup>() {
                @Override
                protected void updateItem(DeviceGroup deviceGroup, boolean empty) {
                    super.updateItem(deviceGroup, empty);
                    if (deviceGroup == null || empty) {
                        setText(null);
                    } else {
                        setText(deviceGroup.getName());
                    }
                }
            };
        });
    }
    /**
     * Handles the action of changing the device name.
     */
    @FXML
    private void changeDeviceName() {
        String newName = deviceNameField.getText();
        device.setName(newName);
        deviceDAO.updateDevice(device.getDeviceID(), newName);
    }
    /**
     * Handles the action of changing the model code of the device.
     */
    @FXML
    private void changeModelCode() {
        String newModelCode = modelCode.getText();
        device.setModelCode(newModelCode);
        deviceDAO.updateModelCode(device.getDeviceID(), newModelCode);
    }
    /**
     * Handles the action of changing the automation settings of the device.
     */
    public void changeAutomation() {
        deviceDAO.updateAutomation(device.getDeviceID());
    }
}

