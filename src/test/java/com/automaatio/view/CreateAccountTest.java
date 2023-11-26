package com.automaatio.view;

import com.automaatio.controller.CreateAccountController;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
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

@ExtendWith(ApplicationExtension.class)
class CreateAccountTest {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private static final UserDAO userDAO = new UserDAO();

    private Text usernameTooltip, firstNameTooltip;
    private TextField usernameField;

    @BeforeAll
    public static void setup() {
        String testUsername = "jdoe12";
        if (userDAO.getObject(testUsername) != null) {
            userDAO.deleteObject(testUsername);
        }
    }

    private void getFields(FxRobot robot) {
        usernameField = robot.lookup("#usernameField").queryAs(TextField.class);

        usernameTooltip = robot.lookup("#usernameTooltip").queryAs(Text.class);
        firstNameTooltip = robot.lookup("#firstNameTooltip").queryAs(Text.class);
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
    }

    @Test
    void createAccountFail(FxRobot robot) {
        getFields(robot);
        robot.clickOn(usernameField).write("jjj");
        assertEquals("Password incorrect", usernameTooltip.getText());

    }
}