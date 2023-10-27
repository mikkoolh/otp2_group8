package com.automaatio.controller.mainpage;

import com.automaatio.components.CreateVBoxColumn;
import com.automaatio.controller.mainpage.clickActions.DevicesClick;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @author Elmo Erla
 */
public class RoomController implements Initializable, IController {
    @FXML
    private Text roomName;
    @FXML
    private ComboBox<Device> deviceComboBox;
    @FXML
    private VBox devicesVBox;
    @FXML
    private Button deleteRoom;

    private final CacheSingleton cache = CacheSingleton.getInstance();
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private DeviceDAO deviceDAO = new DeviceDAO();
    private CreateVBoxColumn deviceRow = new CreateVBoxColumn();

    public RoomController() {}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheSingleton cache = CacheSingleton.getInstance();
        roomName.setText(cache.getRoom().getName());

        deleteRoom.getStyleClass().add("roomDeleteButton");

        populateDevicesDropdown();

        deviceComboBox.setOnAction(event -> {
            Device selectedDevice = deviceComboBox.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceDAO.updateDeviceGroup(selectedDevice.getDeviceID(), cache.getRoom().getDeviceGroupId());
                showDevices();
            }
        });

        deviceComboBox.setPromptText("Select your device to be added");

        showDevices();
    }

    private void populateDevicesDropdown() {
        String currentUser = cache.getUser().getUsername();
        List<Device> devices = deviceGroupDAO.getDevicesNotInGroup(cache.getRoom().getDeviceGroupId(), currentUser);
        deviceComboBox.setItems(FXCollections.observableArrayList(devices));

        deviceComboBox.setCellFactory((comboBox) -> {
            return new ListCell<Device>() {
                @Override
                protected void updateItem(Device item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        });

        deviceComboBox.setConverter(new StringConverter<Device>() {
            @Override
            public String toString(Device device) {
                if (device == null) {
                    return null;
                } else {
                    return device.getName();
                }
            }
            @Override
            public Device fromString(String s) {
                return null;
            }
        });
    }

    public void showDevices() {
        devicesVBox.getChildren().clear();
        List<Device> devices = deviceGroupDAO.getDevicesByRoom(cache.getRoom());
        for (Device device : devices) {
            devicesVBox.getChildren().add(deviceRow.create(device, devicesVBox, new DevicesClick()));
        }
    }

    @FXML
    public void deleteRoom() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Room");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this room?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            List<Device> devicesInRoom = deviceGroupDAO.getDevicesByRoom(cache.getRoom());
            for (Device device : devicesInRoom) {
                device.setDeviceGroup(null);
            }
            deviceGroupDAO.deleteGroup(cache.getRoom().getDeviceGroupId());
        }
    }
}
