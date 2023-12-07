package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.BundleLoader;
import javafx.scene.control.ToggleButton;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code ToggleButtonCreator} class implements the {@code IButton} interface
 * to create a specialized toggle button, typically for controlling the on/off state
 * of a {@link Device}. This class provides methods to instantiate and configure
 * a {@link ToggleButton} based on the state and data of a given {@code Device}.
 *
 * The toggle button's appearance and text change dynamically based on the current
 * state of the device (on or off). It also updates the device's usage data upon toggling.
 */
public class ToggleButtonCreator implements IButton {

    private DeviceDAO deviceDAO = new DeviceDAO();
    private BundleLoader bundleLoader = new BundleLoader();

    /**
     * Creates a {@link ToggleButton} for a given object, typically a {@link Device}.
     * The button's state and appearance are set based on the device's current status.
     *
     * @param object The object (usually a {@link Device}) associated with the toggle button.
     * @param clickActions The {@code ClickActions} instance, not used in the current implementation.
     * @return A configured {@link ToggleButton} for the given object.
     */
    @Override
    public ToggleButton create(Object object, ClickActions clickActions) {
        return createToggleBtn(object);
    }

    /**
     * Private helper method to create and configure a {@link ToggleButton} based on the
     * state of the provided object.
     *
     * @param object The object (usually a {@link Device}) for which the toggle button is created.
     * @return A configured {@link ToggleButton}.
     */
    private ToggleButton createToggleBtn(Object object) {
        ToggleButton toggleButton = new ToggleButton();
        if (object instanceof Device){
            setOnOff(deviceDAO.getObject(((Device) object).getDeviceID()).isOnOff(), object, toggleButton, bundleLoader.loadResourceByUsersLocale());
            toggleButton.getStyleClass().add("toggleBtn");
            toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                setOnOff(isSelected,object, toggleButton, bundleLoader.loadResourceByUsersLocale());
            });
        }
        return toggleButton;
    }

    /**
     * Sets the on/off state of the toggle button and updates the corresponding device's
     * usage data and visual appearance based on the state.
     *
     * @param isSelected The current state of the toggle button.
     * @param object The object (usually a {@link Device}) associated with the toggle button.
     * @param onOff The {@link ToggleButton} to be configured.
     * @param bundle The {@link ResourceBundle} for localization.
     */
    private void setOnOff(boolean isSelected, Object object, ToggleButton onOff, ResourceBundle bundle){
        if (isSelected) {
            switchOnOff(object);
            onOff.setText(bundle.getString("onBtnTxt"));
            Device device = (Device) object;
            device.setUsageData(device.getUsageData() + 1);
            deviceDAO.updateUsageData(device.getDeviceID(), device.getUsageData() + 1);
            System.out.println(device.getUsageData());
            onOff.getStyleClass().remove("toggleBtnOff");
            onOff.getStyleClass().add("toggleBtnOn");
        } else {
            switchOnOff(object);
            onOff.setText(bundle.getString("offBtnTxt"));
            onOff.getStyleClass().remove("toggleBtnOn");
            onOff.getStyleClass().add("toggleBtnOff");
        }
    }

    /**
     * Toggles the on/off state of the device associated with the given object.
     *
     * @param object The object (usually a {@link Device}) whose on/off state is to be toggled.
     */
    public void switchOnOff(Object object) {
        if (object instanceof Device) {
            deviceDAO.updateDeviceOnOff(((Device) object).getDeviceID());
        }
    }
}
