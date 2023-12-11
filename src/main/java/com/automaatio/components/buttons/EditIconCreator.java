package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * The EditIconCreator class extends the IconCreator class
 * to create an image button for editing actions.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class EditIconCreator extends IconCreator {

    /**
     * Creates a button with an 'edit' icon and tooltip text.
     *
     * @return      A button with an 'edit' icon and tooltip text
     */
    public Button create() {
        return super.create("images/edit.png", resourceBundle.getString("editBtnTxt"));
    }
}
