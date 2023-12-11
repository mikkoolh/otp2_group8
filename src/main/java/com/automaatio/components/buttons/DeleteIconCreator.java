package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * The DeleteIconCreator class extends the IconCreator class
 * to create an image button for deletion actions.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */
public class DeleteIconCreator extends IconCreator {

    /**
     * Creates a button with a 'delete' icon and tooltip text.
     *
     * @return      A button with a 'delete' icon and tooltip text
     */
    public Button create() {
        return super.create("images/delete.png", resourceBundle.getString("deleteBtnTxt"));
    }
}
