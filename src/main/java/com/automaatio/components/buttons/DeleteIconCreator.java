package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * The {@code DeleteIconCreator} class extends {@code IconCreator} to specifically create
 * a button designed for deletion actions. This class provides a convenient way to
 * instantiate a button with a predefined delete icon and tooltip text.
 *
 * The icon and tooltip text are defined internally within the method and are specific
 * to deletion actions.
 */
public class DeleteIconCreator extends IconCreator {

    /**
     * Creates a button with a delete icon and tooltip text. This method leverages the
     * {@code create} method of the superclass {@code IconCreator} by passing predefined
     * parameters for a delete button.
     *
     * @return A {@link Button} with a delete icon and tooltip, ready for use in a UI.
     */
    public Button create() {
        return super.create("images/delete.png", resourceBundle.getString("deleteBtnTxt"));
    }
}
