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

/**
 * The DeviceGroupsClick class implements the ClickActions interface and defines click actions specific to device groups in the HomeAutomation application's main page.
 * It handles expand and delete actions for device groups, updating the graphical user interface accordingly.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class DeviceGroupsClick implements ClickActions {
    private CacheSingleton cache = CacheSingleton.getInstance();
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private BundleLoader bundleLoader = new BundleLoader();
    /**
     * Handles the click action when an expand button for a device group is clicked.
     * Sets the selected device group in the cache and loads the corresponding room view in the main pane.
     *
     * @param object The DeviceGroup object associated with the expand action.
     */
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

    /**
     * Handles the click action when a delete button for a device group is clicked.
     * Deletes the device group from the database and removes its corresponding VBox from the main VBox in the user interface.
     *
     * @param object     The DeviceGroup object associated with the delete action.
     * @param mainVBox   The main VBox container in the user interface.
     * @param boxToDelete The specific VBox representing the device group to be deleted from the user interface.
     */
    @Override
    public void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete) {
        deviceGroupDAO.deleteGroup(((DeviceGroup) object).getDeviceGroupId());
        mainVBox.getChildren().remove(boxToDelete);
    }
}
