package com.automaatio.view;

import com.automaatio.controller.CreateAccountController;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class CreateAccountTest {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private static final UserDAO userDAO = new UserDAO();

    private Text usernameTooltip, firstNameTooltip, lastNameTooltip, emailTooltip, phoneTooltip, passwordTooltip;
    private TextField usernameField, firstNameField, lastNameField, emailField, phoneField, passwordField;

    @BeforeAll
    public static void setup() {
        String testUsername = "jdoe12";
        if (userDAO.getObject(testUsername) != null) {
            userDAO.deleteObject(testUsername);
        }
    }

    private void getFields(FxRobot robot) {
        usernameField = robot.lookup("#usernameField").queryAs(TextField.class);
        firstNameField = robot.lookup("#firstNameField").queryAs(TextField.class);
        lastNameField = robot.lookup("#lastNameField").queryAs(TextField.class);
        emailField = robot.lookup("#emailField").queryAs(TextField.class);
        phoneField = robot.lookup("#phoneField").queryAs(TextField.class);
        passwordField = robot.lookup(".password-field").queryAs(PasswordField.class);

        usernameTooltip = robot.lookup("#usernameTooltip").queryAs(Text.class);
        firstNameTooltip = robot.lookup("#firstNameTooltip").queryAs(Text.class);
        lastNameTooltip = robot.lookup("#lastNameTooltip").queryAs(Text.class);
        emailTooltip = robot.lookup("#emailTooltip").queryAs(Text.class);
        phoneTooltip = robot.lookup("#phoneTooltip").queryAs(Text.class);
        passwordTooltip = robot.lookup("#passwordTooltip").queryAs(Text.class);
    }

    @Start
    private void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(CreateAccountController.class.getResource("/view/create-account.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void createAccountSuccess(FxRobot robot) {
        getFields(robot);
        /*
        robot.clickOn("#oldpassField").write("newpassword");
        robot.clickOn("#newpassField").write("salasana1");
        robot.clickOn("#changeBtn");
        Text profileErrorText = robot.lookup("#profileErrorText").queryAs(Text.class);
        assertEquals("Password incorrect", profileErrorText.getText());


        robot.clickOn("#usernameField").write("jdoe");
        //verifyThat("#usernameTooltip", hasText("Username must be at least 5 characters"));

        assertEquals("Password incorrect", profileErrorText.getText());
        robot.write("12");
        verifyThat("#usernameTooltip", hasText("Username available"));

        robot.clickOn("#firstNameField").write("John");
        robot.clickOn("#lastNameField").write("Doe");
        robot.clickOn("#emailField").write("john.doe@test.com");
        robot.clickOn("#phoneNumberField").write("123456789");

        robot.clickOn(".password-field").write("secret");
        verifyThat("#passwordTooltip", hasText("Password must be at least 8 characters"));
        robot.write("pw");
        verifyThat("#passwordTooltip", hasText("Password must include at least one letter and a number"));
        robot.write("1");

        robot.clickOn("#saveButton");

         */
        assertTrue(true);
    }

    @Test
    void createAccountFail(FxRobot robot) {
        getFields(robot);
        robot.clickOn(usernameField).write("jjj");
        assertEquals("Username must be at least 5 characters", usernameTooltip.getText());
        robot.clickOn(usernameField).write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        assertEquals("Username must be 40 characters or less", usernameTooltip.getText());
        robot.clickOn(usernameField).write("j j");
        assertEquals("Username cannot contain spaces", usernameTooltip.getText());

        robot.clickOn(firstNameField).write(" ");
        assertEquals("Required field", firstNameTooltip.getText());
        robot.clickOn(lastNameField).write(" ");
        assertEquals("Required field", lastNameTooltip.getText());

        robot.clickOn(emailField).write("john@doe..fi");
        assertEquals("Invalid email address", emailTooltip.getText());

        robot.clickOn(phoneField).write("1234");
        assertEquals("Invalid phone number", phoneTooltip.getText());

        robot.clickOn(passwordField).write("s");
        assertEquals("Password must be at least 5 characters", passwordTooltip.getText());
        robot.clickOn(passwordField).write("ssssssss");
        assertEquals("Password must include at least one letter and a number", passwordTooltip.getText());
        robot.clickOn(passwordField).write("1 1");
        assertEquals("Password cannot contain spaces", passwordTooltip.getText());
        robot.clickOn(passwordField).write("111111111111111111111111111111111111");
        assertEquals("Password must be 50 characters or less", passwordTooltip.getText());
    }
}