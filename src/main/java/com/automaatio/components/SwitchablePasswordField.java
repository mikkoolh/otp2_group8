package com.automaatio.components;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class SwitchablePasswordField {
    private final TextField textField;
    private final PasswordField passwordField;
    private TextInputControl current;

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

    public void toggle() {
        if (current == textField) {
            current = passwordField;
        } else {
            current = textField;
        }
    }

    public TextInputControl getField() {
        return current;
    }
}
