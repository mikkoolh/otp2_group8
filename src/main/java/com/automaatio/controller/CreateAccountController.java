package com.automaatio.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import com.automaatio.components.buttons.TogglableEyeIconCreator;
import com.automaatio.components.SwitchablePasswordField;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.FormInputValidator;
import com.automaatio.utils.NavigationUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Controller for the registration form
 * @author Matleena Kankaanpää
 * 19.9.2023
 */

public class CreateAccountController {
    private final NavigationUtil nav;
    private final FormInputValidator validator;
    private final UserDAO userDAO;
    private String firstName, lastName, email, phoneNumber, username, password;
    private SwitchablePasswordField switchableField;
    private TextInputControl passwordField;
    @FXML
    private TextField firstNameField, lastNameField, emailField, phoneNumberField, usernameField;
    @FXML
    private Text createAccountErrorText, usernameTooltip, firstNameTooltip, lastNameTooltip, emailTooltip, phoneTooltip, passwordTooltip;
    @FXML
    private Button saveButton;
    @FXML
    private GridPane formGrid;
    private final ResourceBundle resourceBundle;

    public CreateAccountController() {
        nav = new NavigationUtil();
        validator = new FormInputValidator();
        userDAO = new UserDAO();
        resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));
    }

    /**
     * Navigates back to the login page
     * @param event 'Return' button clicked
     * @throws IOException
     */
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        nav.openLoginPage(event);
    }

    /**
     * Event handler for submitting the registration form
     * @param event 'Submit' button clicked
     * @throws IOException
     */
    @FXML
    protected void onSave(ActionEvent event) throws IOException {
        getFieldValues();

        System.out.println(username);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(password);

        // Create a new user
        User user = new User(username, firstName, lastName, phoneNumber, email, BCrypt.hashpw(password, BCrypt.gensalt()), 0, 1);
        System.out.println(user);
        saveUser(user);
        createAccountErrorText.setText("");
        nav.openLoginPage(event);
    }

    // Creates a new user into the database when the save button is clicked
    private void saveUser(User user) {
        try {
            userDAO.addObject(user);
            System.out.println("created user");
        } catch (Exception e) {
            System.out.println(e);
            createAccountErrorText.setText(resourceBundle.getString("createAccountErrorTxt"));
        }
    }

    @FXML
    private void initialize() {
        saveButton.setDisable(true);
        Platform.runLater(() -> usernameField.requestFocus()); // Autofocus

        // Eye button
        Button togglePasswordButton = (new TogglableEyeIconCreator()).create();
        togglePasswordButton.addEventHandler(ActionEvent.ACTION, (e)-> {
            switchableField.toggle();
            formGrid.getChildren().remove(passwordField);
            passwordField = switchableField.getField();
            formGrid.add(passwordField, 1, 10);
        });
        formGrid.add(togglePasswordButton, 2, 10);

        // Switchable password field
        switchableField = new SwitchablePasswordField();
        passwordField = switchableField.getField();
        formGrid.add(passwordField, 1, 10);

        getFieldValues();

        // Set the input tooltips on screen
        validator.validateUsername(username, usernameTooltip);
        validator.validateFirstName(firstName, firstNameTooltip);
        validator.validateLastName(lastName, lastNameTooltip);
        validator.validateEmail(email, emailTooltip);
        validator.validatePhoneNumber(phoneNumber, phoneTooltip);
        validator.validatePassword(password, passwordTooltip);

        // Change listener for username field
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (validator.validateUsername(newValue.trim(), usernameTooltip)) {
                // If the username input passes validation, check if it's already taken
                try {
                    if (userDAO.getObject(newValue.trim()) != null) {
                        usernameTooltip.setText(resourceBundle.getString("usernameTakenTxt"));
                    } else {
                        usernameTooltip.setText(resourceBundle.getString("usernameAvailableTxt"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    createAccountErrorText.setText(resourceBundle.getString("tryAgainTxt"));
                }
                toggleButton();
            }
        });

        // Change listener for first name field
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            validator.validateFirstName(newValue.trim(), firstNameTooltip);
            toggleButton();
        });

        // Change listener for last name field
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            validator.validateLastName(newValue.trim(), lastNameTooltip);
            toggleButton();
        });

        // Change listener for email field
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            validator.validateEmail(newValue.trim(), emailTooltip);
            toggleButton();
        });

        // Change listener for phone number field
        phoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            validator.validatePhoneNumber(newValue.trim().replaceAll("\\s", ""), phoneTooltip);
            toggleButton();
        });

        // Change listener for password field
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            validator.validatePassword(newValue, passwordTooltip);
            toggleButton();
        });
    }

    // Enables/disables the save button depending on whether all fields pass validation
    private void toggleButton() {
        getFieldValues();

        boolean inputOk = validator.validateUsername(username, usernameTooltip)
                && validator.validateFirstName(firstName, firstNameTooltip)
                && validator.validateLastName(lastName, lastNameTooltip)
                && validator.validateEmail(email, emailTooltip)
                && validator.validatePhoneNumber(phoneNumber, phoneTooltip)
                && validator.validatePassword(password, passwordTooltip);

        saveButton.setDisable(!inputOk);
    }

    private void getFieldValues(){
        firstName = firstNameField.getText().trim();
        lastName = lastNameField.getText().trim();
        email = emailField.getText().trim();
        phoneNumber = phoneNumberField.getText().trim().replaceAll("\\s", ""); // Delete spaces
        username = usernameField.getText().trim();
        password = passwordField.getText();
    }
}