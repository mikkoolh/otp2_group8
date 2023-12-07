package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * @author Matleena Kankaanpää
 *
 * The {@code CancelIconCreator} class extends {@code IconCreator} to specifically create
 * a button designed for cancellation actions. This class provides a convenient way to
 * instantiate a button with a predefined cancel icon and tooltip text.
 *
 * The icon and tooltip text are defined internally within the method and are specific
 * to cancellation actions.
 */
public class CancelIconCreator extends IconCreator {

    /**
     * Creates a button with a cancel icon and tooltip text. This method leverages the
     * {@code create} method of the superclass {@code IconCreator} by passing predefined
     * parameters for a cancel button.
     *
     * @return A {@link Button} with a cancel icon and tooltip, ready for use in a UI.
     */
    public Button create() {
        return super.create("images/cancel.png", resourceBundle.getString("cancelBtnTxt"));
    }
}
