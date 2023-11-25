package com.automaatio.view;

import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
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

@ExtendWith(ApplicationExtension.class)

class RoutineControllerTest {

    @BeforeAll
    static void setup() {
        CacheSingleton cache = CacheSingleton.getInstance();
        UserDAO userDAO = new UserDAO();
        User user;

        if (userDAO.getObject("testuser") == null) {
            user = userDAO.addAndReturnObject(new User("testuser", "test", "user", "123456789", "test@email.com", "password123", 0, 1));
        } else {
            user = userDAO.getObject("testuser");
        }

        user.setLocale("en_US");
        cache.setUser(user);
        System.out.println("user: " + cache.getUser().getUsername());
    }

    @Start
    private void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/main-page.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void test(FxRobot robot) {
    }
}