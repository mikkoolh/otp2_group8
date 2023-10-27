package com.automaatio.utils;

import javafx.scene.control.Label;

public class ErrorMessageHandler {
    public void showErrorMessage(String message, Label field) {
        field.setVisible(true);
        field.setText(message);
    }

    public void hideErrorMessage(Label field) {
        field.setVisible(false);
        field.setText("");
    }
}
