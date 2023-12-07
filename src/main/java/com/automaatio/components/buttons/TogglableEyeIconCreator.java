package com.automaatio.components.buttons;

import javafx.scene.control.Button;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Matleena Kankaanpää
 *
 * The {@code TogglableEyeIconCreator} class extends {@code TogglableIconCreator} to create
 * a specialized button that toggles between "hide" and "show" states, typically used in
 * conjunction with password fields. This class provides a convenient way to instantiate
 * a button with icons representing eye-open and eye-closed states, along with corresponding
 * tooltips.
 *
 * The button created by this class is designed to toggle its appearance and function
 * between showing and hiding password content.
 */

public class TogglableEyeIconCreator extends TogglableIconCreator {

    /**
     * Creates a togglable "hide/show" button with eye-open and eye-closed icons.
     * The button toggles between these two states, changing its icon and tooltip
     * accordingly. This button is typically used for controlling the visibility
     * of password fields.
     *
     * @return A {@link Button} with togglable icons for "hide" and "show" actions,
     * styled with a specific appearance.
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
