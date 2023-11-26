package com.automaatio.utils;

import javafx.scene.text.Text;
import java.util.ResourceBundle;

/**
 * User input validation
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class FormInputValidator {
    private final Integer USERNAME_MIN_LENGTH, PASSWORD_MIN_LENGTH,
            USERNAME_MAX_LENGTH, PASSWORD_MAX_LENGTH, FIRSTNAME_MAX_LENGTH, LASTNAME_MAX_LENGTH, PHONE_MIN_LENGTH, PHONE_MAX_LENGTH;

    private final ResourceBundle resourceBundle;
    private final CompoundMessageCreator compoundMessageCreator;

    public FormInputValidator() {
        resourceBundle = ResourceBundle.getBundle("TextResources", (new CurrentLocale().getCurrentLocale()));
        compoundMessageCreator = new CompoundMessageCreator();
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
            errorField.setText(resourceBundle.getString("requiredFieldTxt"));
            return false;
        } else if (includesSpaces(input)) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { resourceBundle.getString("usernameTxt") },
                    "inputCannotContainSpacesTemplate"
            ));
            return false;
        } else if (input.length() < USERNAME_MIN_LENGTH) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { USERNAME_MIN_LENGTH },
                    "usernameMinLengthTemplate"
            ));
            return false;
        } else if (input.length() > USERNAME_MAX_LENGTH) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { USERNAME_MAX_LENGTH },
                    "usernameMaxLengthTemplate"
            ));
            return false;
        }
        return true;
    }

    public boolean validateFirstName(String input, Text errorField) {
        if (input.isEmpty()) {
            errorField.setText(resourceBundle.getString("requiredFieldTxt"));
            return false;
        } else if (input.length() > FIRSTNAME_MAX_LENGTH) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { FIRSTNAME_MAX_LENGTH },
                    "firstNameMaxLengthTemplate"
            ));
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validateLastName(String input, Text errorField) {
        if (input.isEmpty()) {
            errorField.setText(resourceBundle.getString("requiredFieldTxt"));
            return false;
        } else if (input.length() > LASTNAME_MAX_LENGTH) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { LASTNAME_MAX_LENGTH },
                    "lastNameMaxLengthTemplate"
            ));
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validateEmail(String input, Text errorField) {
        if (input.isEmpty()){
            errorField.setText(resourceBundle.getString("requiredFieldTxt"));
            return false;
        } else if (!input.matches("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")) {
            errorField.setText(resourceBundle.getString("invalidEmailTxt"));
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validatePhoneNumber(String input, Text errorField) {
        if (input.isEmpty()){
            errorField.setText(resourceBundle.getString("requiredFieldTxt"));
            return false;
        } else if (!input.matches("^+?[0-9-]{" + PHONE_MIN_LENGTH + "," + PHONE_MAX_LENGTH + "}$")) {
            errorField.setText(resourceBundle.getString("invalidPhoneNumberTxt"));
            return false;
        }
        errorField.setText("");
        return true;
    }

    public boolean validatePassword(String input, Text errorField) {
        if (input.isEmpty()){
            errorField.setText(resourceBundle.getString("requiredFieldTxt"));
            return false;
        } else if (input.length() < PASSWORD_MIN_LENGTH) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { PASSWORD_MIN_LENGTH },
                    "passwordMinLengthTemplate"
            ));
            return false;
        } else if (input.length() > PASSWORD_MAX_LENGTH) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { PASSWORD_MAX_LENGTH },
                    "passwordMaxLengthTemplate"
            ));
            return false;
        } else if (includesSpaces(input)) {
            errorField.setText(compoundMessageCreator.create(
                    new Object[] { resourceBundle.getString("passwordTxt") },
                    "inputCannotContainSpacesTemplate"
            ));
            return false;
        } else if (!input.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{0," + PASSWORD_MAX_LENGTH +"}$")) {
            errorField.setText(resourceBundle.getString("passwordFormatTxt"));
            return false;
        }
        errorField.setText("");
        return true;
    }

    public Boolean includesSpaces(String s) {
        return !s.trim().replaceAll("\\s", "").equals(s);
    }
}