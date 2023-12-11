package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * The TogglableEyeIconCreator class extends the TogglableIconCreator class
 * to create a togglable button with an image of an open eye and an image of
 * a closed eye that switch when the button is clicked.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class TogglableEyeIconCreator extends TogglableIconCreator {

    /**
     * Creates a togglable button with an image of an open eye and an image of a closed eye
     * @return      A togglable button with an image of an open eye and an image of a closed eye
     */
    public Button create() {
        super.create("images/eye-open.png", "images/eye-hidden.png",  resourceBundle.getString("showPasswordTxt"), resourceBundle.getString("hidePasswordTxt"));
        int icon_size = 25; // Icon dimensions
        defaultView.setFitHeight(icon_size);
        altView.setFitHeight(icon_size);
        button.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
        return button;
    }
}
