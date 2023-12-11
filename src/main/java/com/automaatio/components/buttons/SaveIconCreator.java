package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * The SaveIconCreator class extends the IconCreator class
 * to create an image button for save actions.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class SaveIconCreator extends IconCreator {

    /**
     * Creates a button with a 'save' icon and tooltip text.
     *
     * @return      A button with a 'save' icon and a toolip text
     */
    public Button create() {
        return super.create("images/save.png", resourceBundle.getString("saveBtnTxt"));
    }
}
