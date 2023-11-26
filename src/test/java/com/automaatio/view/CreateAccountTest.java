package com.automaatio.view;

import com.automaatio.controller.CreateAccountController;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
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
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class CreateAccountTest {
    private final CacheSingleton cache = CacheSingleton.getInstance();

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
    void createAccount(FxRobot robot) {
        robot.clickOn("#usernameField").write("jdoe");
        verifyThat("#usernameTooltip", hasText("Username must be at least 5 characters"));
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
    }
}