package com.automaatio.components;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

/**
 * The {@code SwitchablePasswordField} class provides a component that toggles between
 * a {@link TextField} and a {@link PasswordField}. This is particularly useful in
 * scenarios where a user might need to switch between viewing and hiding their password input.
 *
 * The class maintains both a text field and a password field, synchronizing their content.
 * The current field being displayed can be toggled, allowing the user to switch between
 * seeing the typed password as plain text or as masked characters.
 */
public class SwitchablePasswordField {
    private final TextField textField;
    private final PasswordField passwordField;
    private TextInputControl current;

    /**
     * Constructs a new {@code SwitchablePasswordField} with a {@link TextField} and
     * a {@link PasswordField}. The text property of both fields is bidirectionally bound,
     * ensuring they display the same content.
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
     * Toggles the visibility of the password between plain text and masked characters.
     * Switches the current field from a {@link TextField} to a {@link PasswordField} or vice versa.
     */
    public void toggle() {
        if (current == textField) {
            current = passwordField;
        } else {
            current = textField;
        }
    }

    /**
     * Gets the current active field, which can be either a {@link TextField} or a {@link PasswordField},
     * depending on the toggle state.
     *
     * @return The currently active {@link TextInputControl}, either the text field or the password field.
     */
    public TextInputControl getField() {
        return current;
    }
}
