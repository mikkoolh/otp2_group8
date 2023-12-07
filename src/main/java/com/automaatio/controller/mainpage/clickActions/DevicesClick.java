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

/**
 * The DevicesClick class implements the ClickActions interface and defines click actions specific to devices in the HomeAutomation application's main page.
 * It handles expand and delete actions for devices, updating the graphical user interface (GUI) accordingly.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class DevicesClick implements ClickActions {
    private DeviceDAO deviceDAO = new DeviceDAO();
    private CacheSingleton cache = CacheSingleton.getInstance();
    private BundleLoader bundleLoader = new BundleLoader();

    /**
     * Handles the click action when an expand button for a device is clicked.
     * Sets the selected device in the cache and loads the corresponding device view in the main pane.
     *
     * @param object The Device object associated with the expand action.
     */
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
    /**
     * Handles the click action when a delete button for a device is clicked.
     * Deletes the device from the database and removes its corresponding VBox from the main VBox in the user interface.
     *
     * @param object     The Device object associated with the delete action.
     * @param mainVBox   The main VBox container in the user interface.
     * @param boxToDelete The specific VBox representing the device to be deleted from the user interface.
     */
    @Override
    public void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete) {
        deviceDAO.deleteDevice(((Device) object).getDeviceID());
        mainVBox.getChildren().remove(boxToDelete);
    }
}