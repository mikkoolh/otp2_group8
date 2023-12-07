package com.automaatio.components.buttons;

import com.automaatio.components.buttons.IButton;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.utils.BundleLoader;
import javafx.scene.control.Button;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code ExpandButtonCreator} class implements the {@code IButton} interface
 * to create a button that is intended for expanding or opening an item, such as a
 * {@link Device} or {@link DeviceGroup}. The text label of the button is determined
 * based on the type of object it is associated with.
 *
 * This class is designed to dynamically set the button's label and action based on
 * the type of the provided object, making it versatile for different contexts where
 * an expand or open action is required.
 */
public class ExpandButtonCreator implements IButton {
    private String label;
    private BundleLoader bundleLoader = new BundleLoader();

    /**
     * Creates a button with a label and action based on the type of the provided object.
     * The button's label and action change depending on whether the object is an instance
     * of {@link Device} or {@link DeviceGroup}.
     *
     * @param object The object associated with the button, used to determine the button's label and action.
     * @param clickActions The {@code ClickActions} instance defining the expand action.
     * @return A {@link Button} configured with an expand/open functionality.
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
