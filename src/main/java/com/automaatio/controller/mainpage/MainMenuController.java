package com.automaatio.controller.mainpage;

import com.automaatio.controller.mainpage.menu.DevicesController;
import com.automaatio.controller.mainpage.menu.ProfileController;
import com.automaatio.controller.mainpage.menu.RoomsController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable, IController {
    private static ProfileController menuProfileController;
    private static DevicesController menuDevicesController;
    private static RoomsController menuRoomsController;

    private static RoomController mainRoomController;
    private static DeviceController mainDeviceController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void setMenuProfileController(ProfileController controller) {
        menuProfileController = controller;
    }

    public static void setDevicesController(DevicesController controller){
        menuDevicesController = controller;
    }
    public static void setRoomsController(RoomsController controller){
        menuRoomsController = controller;
    }

    public static void setRoomController(RoomController controller){
        mainRoomController = controller;
    }

    public static void setMainDeviceController(DeviceController controller){
        mainDeviceController = controller;
    }

    public void setELPriceInUI(Double price) {
        menuProfileController.setElPriceNow(price);
    }

    public static void reloadMenuRooms() {
        menuRoomsController.show();
    }

    public static void reloadMenuDevices() {
        menuDevicesController.show();
    }

    public static void reloadMainDevices(){
        mainDeviceController.reload();

    }
    public static void reloadMainRooms(){
        mainRoomController.reload();
    }
}