package com.automaatio.components;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

/**
 * The SwitchablePasswordField class is used to create a password
 * field with the functionality to switch between displaying the
 * content of the field as either plain or hidden text.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class SwitchablePasswordField {
    private final TextField textField;
    private final PasswordField passwordField;
    private TextInputControl current;

    /**
     * Class constructor
     */
    public SwitchablePasswordField() {
        textField = new TextField();
        passwordField = new PasswordField();
        passwordField.textProperty().bindBidirectional(textField.textProperty());
        textField.getStyleClass().add("input-field");
        textField.getStyleClass().add("password-field");
        passwordField.getStyleClass().add("input-field");
        passwordField.getStyleClass().add("password-field");
        current = passwordField;
    }

    /**
     * Changes the field to a PasswordField if it was previously a TextField
     * and vice versa.
     */
    public void toggle() {
        if (current == textField) {
            current = passwordField;
        } else {
            current = textField;
        }
    }

    /**
     * Returns the field in its current state (TextField or PasswordField)
     * @return      The current field
     */
    public TextInputControl getField() {
        return current;
    }
}
