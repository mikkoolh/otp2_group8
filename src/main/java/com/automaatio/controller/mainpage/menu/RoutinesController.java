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

public class RoutinesController implements Initializable {

    private final CacheSingleton cache = CacheSingleton.getInstance();

    private final CreateVBoxColumn deviceRow = new CreateVBoxColumn();

    @FXML
    private VBox routinesVBox;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showRoutines();
    }
}