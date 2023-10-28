package com.automaatio.components.buttons;

import com.automaatio.components.buttons.IButton;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import javafx.scene.control.Button;

import java.util.Locale;
import java.util.ResourceBundle;

public class ExpandButtonCreator implements IButton {
    private String label;
    @Override
    public Button create(Object object, ClickActions clickActions) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

        if (object instanceof Device){
            label = resourceBundle.getString("editBtnTxt");
        } else if (object instanceof DeviceGroup) {
            label = resourceBundle.getString("openBtnTxt");
        }
        Button editBtn = new Button(label);
        editBtn.getStyleClass().add("editBtn");
        editBtn.setOnAction(event -> clickActions.onExpandClick(object));
        return editBtn;
    }
}
