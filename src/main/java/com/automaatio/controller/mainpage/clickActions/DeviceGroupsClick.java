package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeviceGroupsClick implements ClickActions {
    private CacheSingleton cache = CacheSingleton.getInstance();
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private BundleLoader bundleLoader = new BundleLoader();
    @Override
    public void onExpandClick(Object object) {
        cache.setRoom((DeviceGroup) object);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/room.fxml"));
            loader.setResources(bundleLoader.loadResourceByUsersLocale());
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
