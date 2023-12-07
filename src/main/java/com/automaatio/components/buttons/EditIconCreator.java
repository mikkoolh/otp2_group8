package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * The {@code EditIconCreator} class extends {@code IconCreator} to specifically create
 * a button designed for edit actions. This class provides a convenient way to
 * instantiate a button with a predefined edit icon and tooltip text.
 *
 * The icon and tooltip text are defined internally within the method and are specific
 * to edit actions.
 */
public class EditIconCreator extends IconCreator {

    /**
     * Creates a button with an edit icon and tooltip text. This method leverages the
     * {@code create} method of the superclass {@code IconCreator} by passing predefined
     * parameters for an edit button.
     *
     * @return A {@link Button} with an edit icon and tooltip, ready for use in a UI.
     */
    public Button create() {
        return super.create("images/edit.png", resourceBundle.getString("editBtnTxt"));
    }
}
