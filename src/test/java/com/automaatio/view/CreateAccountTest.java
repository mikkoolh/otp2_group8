package com.automaatio.view;

import com.automaatio.controller.CreateAccountController;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code CreateAccountTest} class contains TestFX-based UI tests for
 * the account creation functionality in the application. It uses {@link FxRobot} to
 * simulate user interactions with the UI.
 *
 * This test class is designed to ensure that the functionality for creating a new user account
 * works as expected. It includes tests for successful account creation, validation of user input,
 * and handling of duplicate account creation attempts.
 */
@ExtendWith(ApplicationExtension.class)
class CreateAccountTest {
    private static final UserDAO userDAO = new UserDAO();
    private Text usernameTooltip, firstNameTooltip, lastNameTooltip, emailTooltip, phoneTooltip, passwordTooltip, statusMessage;
    private TextField usernameField, firstNameField, lastNameField, emailField, phoneField, passwordField;
    private static final String testUser1 = "testuser-mk-1", testUser2 = "testuser-mk-2";

    /**
     * Setup method to prepare the testing environment. It ensures that test users are
     * properly set up or removed as needed.
     */
    @BeforeAll
    public static void setup() {
        // Delete user testuser1 if exists
        if (userDAO.getObject(testUser1) != null) {
            userDAO.deleteObject(testUser1);
        }
        
        // Create testuser2 if doesn't exist
        if (userDAO.getObject(testUser2) == null) {
            userDAO.addObject(new User(testUser2, "", "", "", "", "", 0, 1));
        }
    }

    private void getFields(FxRobot robot) {
        usernameField = robot.lookup("#usernameField").queryAs(TextField.class);
        firstNameField = robot.lookup("#firstNameField").queryAs(TextField.class);
        lastNameField = robot.lookup("#lastNameField").queryAs(TextField.class);
        emailField = robot.lookup("#emailField").queryAs(TextField.class);
        phoneField = robot.lookup("#phoneNumberField").queryAs(TextField.class);
        passwordField = robot.lookup(".password-field").queryAs(PasswordField.class);
        statusMessage = robot.lookup("#createAccountErrorText").queryAs(Text.class);

        usernameTooltip = robot.lookup("#usernameTooltip").queryAs(Text.class);
        firstNameTooltip = robot.lookup("#firstNameTooltip").queryAs(Text.class);
        lastNameTooltip = robot.lookup("#lastNameTooltip").queryAs(Text.class);
        emailTooltip = robot.lookup("#emailTooltip").queryAs(Text.class);
        phoneTooltip = robot.lookup("#phoneTooltip").queryAs(Text.class);
        passwordTooltip = robot.lookup("#passwordTooltip").queryAs(Text.class);
    }

    /**
     * Initializes the JavaFX environment for the test. This method is called
     * before each test execution to set up the UI components.
     *
     * @param stage The primary stage for this application.
     * @throws IOException if there is an error during setup.
     */
    @Start
    private void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(CreateAccountController.class.getResource("/view/create-account.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test method to verify successful account creation. It simulates user
     * interactions for creating an account and asserts the success message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void createAccountSuccess(FxRobot robot) {
        getFields(robot);
        robot.clickOn("#usernameField").write(testUser1);
        assertEquals("Username available", usernameTooltip.getText());
        robot.clickOn("#firstNameField").write("John");
        robot.clickOn("#lastNameField").write("Doe");
        robot.clickOn("#emailField").write("john.doe@test.com");
        robot.clickOn("#phoneNumberField").write("123456789");
        robot.clickOn(".password-field").write("secretpw1");
        robot.clickOn("#saveButton");
        assertEquals("Account created successfully", statusMessage.getText());
    }

    /**
     * Test method to verify account creation failure due to invalid input. It simulates user
     * interactions with various invalid inputs and asserts the appropriate error messages.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void createAccountFail(FxRobot robot) {
        getFields(robot);
        robot.clickOn(usernameField).write("jjj");
        assertEquals("Username must be at least 5 characters", usernameTooltip.getText());
        robot.write("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        assertEquals("Username must be 40 characters or less", usernameTooltip.getText());
        robot.write("j j");
        assertEquals("Username cannot contain spaces", usernameTooltip.getText());

        robot.clickOn(firstNameField).write(" ");
        assertEquals("Required field", firstNameTooltip.getText());
        robot.clickOn(firstNameField).write("ttttttttttttttttttttttttttttttttttttttttt");
        assertEquals("First name must be 40 characters or less", firstNameTooltip.getText());

        robot.clickOn(lastNameField).write(" ");
        assertEquals("Required field", lastNameTooltip.getText());
        robot.clickOn(lastNameField).write("ttttttttttttttttttttttttttttttttttttttttt");
        assertEquals("Last name must be 40 characters or less", lastNameTooltip.getText());

        robot.clickOn(emailField).write("john.doe@test..fi");
        assertEquals("Invalid email address", emailTooltip.getText());

        robot.clickOn(phoneField).write("1234");
        assertEquals("Invalid phone number", phoneTooltip.getText());

        robot.clickOn(passwordField).write("s");
        assertEquals("Password must be at least 8 characters", passwordTooltip.getText());
        robot.write("ssssssss");
        assertEquals("Password must include at least one letter and a number", passwordTooltip.getText());
        robot.write("1 1");
        assertEquals("Password cannot contain spaces", passwordTooltip.getText());
        robot.write("1111111111111111111111111111111111111111");
        assertEquals("Password must be 50 characters or less", passwordTooltip.getText());
    }

    /**
     * Test method to verify failure when attempting to create an account with a username
     * that already exists. It simulates user interactions for creating a duplicate account
     * and asserts the error message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void createDuplicateAccountFail(FxRobot robot) {
        getFields(robot);
        robot.clickOn(usernameField).write(testUser2);
        assertEquals("Username already taken", usernameTooltip.getText());
    }

    /**
     * Teardown method to clean up after all tests. It removes any test users created
     * during the tests to ensure a clean state for subsequent tests.
     */
    @AfterAll
    static void endTests() {
        if (userDAO.getObject(testUser1) != null) {
            userDAO.deleteObject(testUser1);
        }
        if (userDAO.getObject(testUser2) != null) {
            userDAO.deleteObject(testUser2);
        }
    }
}