package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;

import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.ResourceBundle;

public class DevicesClick implements ClickActions {
    private DeviceDAO deviceDAO = new DeviceDAO();
    private CacheSingleton cache = CacheSingleton.getInstance();
    private BundleLoader bundleLoader = new BundleLoader();

    @Override
    public void onExpandClick(Object object) {
        cache.setDevice((Device) object);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/device.fxml"));
            loader.setResources(bundleLoader.loadResourceByUsersLocale());
            Parent newView = loader.load(); // TODO: Antaa virhettä nyt painatessa laitteen edit-nappia. Virheviesti heittää tänne.
            cache.getMainPane().getChildren().clear();
            cache.getMainPane().getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete) {
        deviceDAO.deleteDevice(((Device) object).getDeviceID());
        mainVBox.getChildren().remove(boxToDelete);
    }
}