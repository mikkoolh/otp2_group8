package com.automaatio.components.buttons;

import javafx.scene.control.Button;

/**
 * @author Matleena Kankaanpää
 * 7.10.2023
 *
 * Creator class for a togglable "hide/show" button used with password fields
 */

public class TogglableEyeIconCreator extends TogglableIconCreator {

    public Button create() {
        super.create("images/eye-open.png", "images/eye-hidden.png", "Show password", "Hide password");
        int icon_size = 25; // Icon dimensions
        defaultView.setFitHeight(icon_size);
        altView.setFitHeight(icon_size);
        button.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
        return button;
    }
}
