package com.automaatio.controller;

import com.automaatio.components.localeSelector.LocaleSelector;
import com.automaatio.components.buttons.TogglableEyeIconCreator;
import com.automaatio.components.SwitchablePasswordField;
import com.automaatio.model.database.*;
import com.automaatio.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller for the login form.
 * Manages user login, authentication, and navigation between login and account creation.
 *
 * @author Matleena Kankaanpää
 * @since 8.9.2023
 */
public class LoginController {
    @FXML
    private Text loginErrorText;

    @FXML
    private TextField usernameField;
    @FXML
    private VBox lowerLoginVBox;

    @FXML
    private Button loginButton;
    private SwitchablePasswordField switchableField;
    private TextInputControl passwordField;
    @FXML
    private GridPane loginFormGrid;

    private final CacheSingleton cache = CacheSingleton.getInstance();

    private final NavigationUtil nav;
    private final UserDAO userDAO;
    private ResourceBundle resourceBundle;
    /**
     * Default constructor.
     * Initializes navigation utilities and UserDAO.
     */
    public LoginController() {
        nav = new NavigationUtil();
        userDAO = new UserDAO();
    }
    /**
     * Initializes the controller after FXML loading.
     * Configures input fields, buttons, and event listeners.
     */
    @FXML
    private void initialize() {
        cache.setCurrentLoader(new FXMLLoader(getClass().getResource("/view/login.fxml")));
        resourceBundle = ResourceBundle.getBundle("TextResources", cache.getTempLocale());
        loginButton.setDisable(true);
        loginButton.setPadding(new Insets(7, 40, 7, 40));

        // Eye button
        Button togglePasswordButton = (new TogglableEyeIconCreator()).create();
        togglePasswordButton.addEventHandler(ActionEvent.ACTION, (e)-> {
            switchableField.toggle();
            loginFormGrid.getChildren().remove(passwordField);
            passwordField = switchableField.getField();
            loginFormGrid.add(passwordField, 2, 1);
        });
        loginFormGrid.add(togglePasswordButton, 3, 1);

        // Switchable password field
        switchableField = new SwitchablePasswordField();
        passwordField = switchableField.getField();
        loginFormGrid.add(passwordField, 2, 1);
        lowerLoginVBox.getChildren().add(new LocaleSelector().getComboBox());

        // Change listeners for input fields
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });
        //languageGrid.add(localeSelector.getComboBox(),2,0);

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    onLoginClick(new ActionEvent(passwordField, passwordField));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Handles the login button click event.
     * Validates user credentials, performs login, and navigates to the main page on success.
     * Displays appropriate error messages for invalid inputs or authentication failures.
     *
     * @param event The ActionEvent triggered by the login button click.
     * @throws IOException If an I/O error occurs during the navigation to the main page.
     */
    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        try {
            User user = userDAO.getObject(username);

            if (user == null) {
                // User not found
                loginErrorText.setText(resourceBundle.getString("usernameNotFoundTxt"));
                System.out.println("user not found");
            } else {
                // User exists, check password
                System.out.println("user exists");
                System.out.println("user: " + username);
                System.out.println("password: " + password);

                if (BCrypt.checkpw(password, user.getPassword())) {
                    System.out.println("password correct");
                    cache.setUser(userDAO.getObject(username));
                    if(user.getLocale().toString().equals("ar_SA")) {
                        cache.setDirection(NodeOrientation.RIGHT_TO_LEFT);
                    } else {
                        cache.setDirection(NodeOrientation.LEFT_TO_RIGHT);
                    }
                    loginErrorText.setText("");
                    nav.openMainPage(event);
                } else {
                    loginErrorText.setText(resourceBundle.getString("wrongPasswordTxt"));
                    System.out.println("wrong password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Database connection error
            loginErrorText.setText(resourceBundle.getString("tryAgainTxt"));
        }
    }
    /**
     * Handles the create account button click event.
     * Navigates to the account creation page.
     *
     * @param event The ActionEvent triggered by the create account button click.
     * @throws IOException If an I/O error occurs during the navigation to the account creation page.
     */
    @FXML
    protected void onCreateAccountClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/create-account.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("TextResources", cache.getTempLocale()));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Disables the login button and clears the error message when both fields are empty.
     */
    private void updateUI() {
        boolean clickable = (!usernameField.getText().trim().isEmpty())
                && (!passwordField.getText().isEmpty());
        loginButton.setDisable(!clickable);

        if (!clickable) {
            loginErrorText.setText("");
        }
    }
}