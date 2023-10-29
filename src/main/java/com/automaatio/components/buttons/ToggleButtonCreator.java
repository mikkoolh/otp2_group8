package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import javafx.scene.control.ToggleButton;
import java.util.Locale;
import java.util.ResourceBundle;

public class ToggleButtonCreator implements IButton {

    private DeviceDAO deviceDAO = new DeviceDAO();
    @Override
    public ToggleButton create(Object object, ClickActions clickActions) {
        return createToggleBtn(object);
    }

    private ToggleButton createToggleBtn(Object object) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));
        ToggleButton toggleButton = new ToggleButton();
        if (object instanceof Device){
            setOnOff(deviceDAO.getObject(((Device) object).getDeviceID()).isOnOff(), object, toggleButton, resourceBundle);
            toggleButton.getStyleClass().add("toggleBtn");
            toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                setOnOff(isSelected,object, toggleButton, resourceBundle);
            });
        }
        return toggleButton;
    }

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

    public void switchOnOff(Object object) {
        if (object instanceof Device) {
            deviceDAO.updateDeviceOnOff(((Device) object).getDeviceID());
        }
    }
}
