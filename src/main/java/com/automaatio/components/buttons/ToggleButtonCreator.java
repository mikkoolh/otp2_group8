package com.automaatio.components.buttons;

import com.automaatio.components.buttons.IButton;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import javafx.scene.control.ToggleButton;

public class ToggleButtonCreator implements IButton {
    private final String onTxt = "On", offTxt = "Off";
    private final int btnWidth = 50;
    private DeviceDAO deviceDAO = new DeviceDAO();
    @Override
    public ToggleButton create(Object object, ClickActions clickActions) {
        return createToggleBtn(object);
    }

    private ToggleButton createToggleBtn(Object object) {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setPrefWidth(btnWidth);
        if (object instanceof Device){
            setOnOff(deviceDAO.getObject(((Device) object).getDeviceID()).isOnOff(), object, toggleButton);
            toggleButton.getStyleClass().add("toggleBtn");
            toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                setOnOff(isSelected,object, toggleButton);
            });
        }
        return toggleButton;
    }

    private void setOnOff(boolean isSelected, Object object, ToggleButton onOff){
        if (isSelected) {
            switchOnOff(object);
            onOff.setText(onTxt);
            Device device = (Device) object;
            device.setUsageData(device.getUsageData() + 1);
            deviceDAO.updateUsageData(device.getDeviceID(), device.getUsageData() + 1);
            System.out.println(device.getUsageData());
            onOff.getStyleClass().remove("toggleBtnOff");
            onOff.getStyleClass().add("toggleBtnOn");
        } else {
            switchOnOff(object);
            onOff.setText(offTxt);
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
