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

@ExtendWith(ApplicationExtension.class)
class CreateAccountTest {
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
        robot.clickOn("#usernameField").write("testuser");
        robot.clickOn("#firstNameField").write("name");
    }
}