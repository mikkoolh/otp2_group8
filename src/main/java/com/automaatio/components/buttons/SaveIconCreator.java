package com.automaatio.components.buttons;

import javafx.scene.control.Button;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code SaveIconCreator} class extends {@code IconCreator} to specifically create
 * a button designed for save actions. This class provides a convenient way to
 * instantiate a button with a predefined save icon and tooltip text.
 *
 * The icon and tooltip text are defined internally within the method and are specific
 * to save actions.
 */
public class SaveIconCreator extends IconCreator {

    /**
     * Creates a button with a save icon and tooltip text. This method leverages the
     * {@code create} method of the superclass {@code IconCreator} by passing predefined
     * parameters for a save button.
     *
     * @return A {@link Button} with a save icon and tooltip, ready for use in a UI.
     */
    public Button create() {
        return super.create("images/save.png", resourceBundle.getString("saveBtnTxt"));
    }
}
