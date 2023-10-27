package com.automaatio.utils;

import javafx.scene.text.Text;

/**
 * User input validation
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class FormInputValidator {
    private final Integer USERNAME_MIN_LENGTH, PASSWORD_MIN_LENGTH,
            USERNAME_MAX_LENGTH, PASSWORD_MAX_LENGTH, FIRSTNAME_MAX_LENGTH, LASTNAME_MAX_LENGTH, PHONE_MIN_LENGTH, PHONE_MAX_LENGTH;

    public FormInputValidator() {
        this.USERNAME_MIN_LENGTH = 5;
        this.USERNAME_MAX_LENGTH = 40;
        this.PASSWORD_MIN_LENGTH = 8;
        this.PASSWORD_MAX_LENGTH = 50;
        this.FIRSTNAME_MAX_LENGTH = 40;
        this.LASTNAME_MAX_LENGTH = 40;
        this.PHONE_MIN_LENGTH = 7;
        this.PHONE_MAX_LENGTH = 15;
    }
    public boolean validateUsername(String input, Text errorField) {
        if (input.isEmpty()) {
            errorField.setText("Required field");
            return false;
        } else if (includesSpaces(input)) {
            errorField.setText("Username cannot contain spaces");
            return false;
        } else if (input.length() < USERNAME_MIN_LENGTH) {
            errorField.setText("Username must be at least " + USERNAME_MIN_LENGTH + " characters");
            return false;
        } else if (input.length() > USERNAME_MAX_LENGTH) {
            errorField.setText("Username must be " + USERNAME_MAX_LENGTH + " characters or less");
            return false;
        }
        return true;
    }

    public boolean validateFirstName(String input, Text errorField) {
        if (input.isEmpty()) {
            errorField.setText("Required field");
            return false;
        } else if (input.length() > FIRSTNAME_MAX_LENGTH) {
            errorField.setText("First name must be " + FIRSTNAME_MAX_LENGTH + " characters or less");
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validateLastName(String input, Text errorField) {
        if (input.isEmpty()) {
            errorField.setText("Required field");
            return false;
        } else if (input.length() > LASTNAME_MAX_LENGTH) {
            errorField.setText("Last name must be " + LASTNAME_MAX_LENGTH + " characters or less");
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validateEmail(String input, Text errorField) {
        if (input.isEmpty()){
            errorField.setText("Required field");
            return false;
        } else if (!input.matches("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")) {
            errorField.setText("Invalid email address");
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validatePhoneNumber(String input, Text errorField) {
        if (input.isEmpty()){
            errorField.setText("Required field");
            return false;
        } else if (!input.matches("^+?[0-9-]{" + PHONE_MIN_LENGTH + "," + PHONE_MAX_LENGTH + "}$")) {
            errorField.setText("Invalid phone number");
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validatePassword(String input, Text errorField) {
        if (input.isEmpty()){
            errorField.setText("Required field");
            return false;
        } else if (input.length() < PASSWORD_MIN_LENGTH) {
            errorField.setText("Password must be at least " + PASSWORD_MIN_LENGTH + " characters");
            return false;
        } else if (input.length() > PASSWORD_MAX_LENGTH) {
            errorField.setText("Password must be " + PASSWORD_MAX_LENGTH + " characters or less");
            return false;
        } else if (!input.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{0," + PASSWORD_MAX_LENGTH +"}$")) {
            errorField.setText("Password must contain at least one letter and a number");
            return false;
        } else if (includesSpaces(input)) {
            errorField.setText("Password cannot contain spaces");
            return false;
        }
        errorField.setText("");
        return true;
    }

    public Boolean includesSpaces(String s) {
        return !s.trim().replaceAll("\\s", "").equals(s);
    }
}