package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.utils.BundleLoader;
import javafx.scene.control.Button;

/**
 * The ExpandButtonCreator class implements the IButton interface
 * to create a button for expanding containers or items.
 *
 * @author Elmo Erla, Mikko Hänninen, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */

public class ExpandButtonCreator implements IButton {
    private String label;
    private BundleLoader bundleLoader = new BundleLoader();

    /**
     * Creates a button for expanding containers or items. The text on the
     * button will be localized and will vary depending on whether the object
     * to be expanded is an instance of the Device or DeviceGroup class.
     *
     * @param object        The object associated with the button
     * @param clickActions  The action to be executed when the button is clicked
     * @return
     */
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
