package com.automaatio.utils;

import javafx.scene.control.Label;

/**
 * The ErrorMessageHandler class streamlines the process of generating
 * error messages and controlling their visibility.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class ErrorMessageHandler {

    /**
     * Sets the given label as visible and fills it with the given text
     *
     * @param message The text to be displayed
     * @param field The label in which the message is displayed
     */
    public void showErrorMessage(String message, Label field) {
        field.setVisible(true);
        field.setText(message);
    }

    /**
     * Clears all text from the given label and sets it as invisible
     *
     * @param field The label to be cleared
     */
    public void hideErrorMessage(Label field) {
        field.setVisible(false);
        field.setText("");
    }
}
