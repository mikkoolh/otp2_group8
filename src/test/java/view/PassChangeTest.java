package view;

import com.automaatio.controller.mainpage.ProfileController;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class PassChangeTest extends ApplicationTest {
    static UserDAO userDAO = new UserDAO();
    @BeforeAll
    static void setup() {
        CacheSingleton cache = CacheSingleton.getInstance();
        User user;

        if (userDAO.getObject("testaaja") == null) {
            user = new User("testaaja", "Testaaja", "Test", "0505551122", "testi@testi.fi", BCrypt.hashpw("oldpassword", BCrypt.gensalt()), 20, 1);
            user.setLocale("en_US");
            userDAO.addObject(user);
            cache.setUser(user);
            System.out.println("user: " + cache.getUser().getUsername());
        } else {
            user = userDAO.getObject("testaaja");
            cache.setUser(user);
        }
    }
    @AfterAll
    static void teardown() {
        userDAO.deleteObjectByUserame("testaaja");
    }
    @Start
    public void start(Stage stage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("/view/user-profile.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @Test
    void testPasswordChange(FxRobot robot) {
        robot.clickOn("#oldpassField").write("oldpassword");
        robot.clickOn("#newpassField").write("newpassword");
        robot.clickOn("#changeBtn");
        Text profileErrorText = robot.lookup("#profileErrorText").queryAs(Text.class);
        assertEquals("Password succesfully changed", profileErrorText.getText());
    }
    @Test
    void testWrongPasswordBeforeChange(FxRobot robot) {
        robot.clickOn("#oldpassField").write("wrongpassword");
        robot.clickOn("#newpassField").write("newpassword");
        robot.clickOn("#changeBtn");
        Text profileErrorText = robot.lookup("#profileErrorText").queryAs(Text.class);
        assertEquals("Password incorrect", profileErrorText.getText());
    }

    @Test
    void testShortNewPassword(FxRobot robot) {
        Random random = new Random();
        int rand = random.nextInt(8);

        StringBuilder newpass = new StringBuilder();
        newpass.append("n".repeat(rand));

        robot.clickOn("#oldpassField").write("oldpassword");
        robot.clickOn("#newpassField").write(newpass.toString());
        robot.clickOn("#changeBtn");
        Text profileErrorText = robot.lookup("#profileErrorText").queryAs(Text.class);
        assertEquals("Password empty or too short", profileErrorText.getText());
    }
}
