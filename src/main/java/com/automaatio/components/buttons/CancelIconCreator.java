package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * The CancelIconCreator class extends the IconCreator class
 * to create an image button for cancelling actions.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class CancelIconCreator extends IconCreator {

    /**
     * Creates a button with a cancel icon and tooltip text.
     *
     * @return A button with a cancel icon and tooltip text
     */
    public Button create() {
        return super.create("images/cancel.png", resourceBundle.getString("cancelBtnTxt"));
    }
}
