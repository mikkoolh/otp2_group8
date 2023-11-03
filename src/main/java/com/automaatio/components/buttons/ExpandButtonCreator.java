package com.automaatio.components.buttons;

import com.automaatio.components.buttons.IButton;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.utils.BundleLoader;
import javafx.scene.control.Button;

import java.util.Locale;
import java.util.ResourceBundle;

public class ExpandButtonCreator implements IButton {
    private String label;
    private BundleLoader bundleLoader = new BundleLoader();
    @Override
    public Button create(Object object, ClickActions clickActions) {
        if (object instanceof Device){
            label = bundleLoader.loadResourceByUsersLocale().getString("editBtnTxt");
        } else if (object instanceof DeviceGroup) {
            label = bundleLoader.loadResourceByUsersLocale().getString("openBtnTxt");
        }
        Button editBtn = new Button(label);
        editBtn.getStyleClass().add("expandBtn");
        editBtn.setOnAction(event -> clickActions.onExpandClick(object));
        return editBtn;
    }
}
