package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class DeviceGroupsClick implements ClickActions {
    private CacheSingleton cache = CacheSingleton.getInstance();
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    @Override
    public void onExpandClick(Object object) {
        cache.setRoom((DeviceGroup) object);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/room.fxml"));
            Parent newView = loader.load();
            cache.getMainPane().getChildren().clear();
            cache.getMainPane().getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete) {
        deviceGroupDAO.removeDeviceFromGroup(cache.getRoom(),(Device) object);
        mainVBox.getChildren().remove(boxToDelete);
    }
}
