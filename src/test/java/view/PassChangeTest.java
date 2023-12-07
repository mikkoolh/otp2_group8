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


/**
 * @author Nikita Nossenko
 *
 * The {@code PassChangeTest} class contains TestFX-based UI tests for
 * the password change functionality in the application. It uses {@link FxRobot} to
 * simulate user interactions with the UI.
 *
 * This test class is designed to ensure that the functionality for changing a user's
 * password works as expected. It includes tests for successful password change,
 * incorrect current password, and validation of new password length.
 */
@ExtendWith(ApplicationExtension.class)
public class PassChangeTest extends ApplicationTest {
    static UserDAO userDAO = new UserDAO();

    /**
     * Setup method to prepare the testing environment. It ensures a test user is
     * available and sets up the necessary environment in the {@link CacheSingleton}.
     */
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

    /**
     * Teardown method to clean up after all tests. It removes the test user from
     * the database to ensure a clean state for subsequent tests.
     */
    @AfterAll
    static void teardown() {
        userDAO.deleteObjectByUserame("testaaja");
    }

    /**
     * Initializes the JavaFX environment for the test. This method is called
     * before each test execution to set up the UI components.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error during setup.
     */
    @Start
    public void start(Stage stage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("/view/user-profile.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test method to verify successful password change. It simulates user
     * interactions for changing the password and asserts the success message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void testPasswordChange(FxRobot robot) {
        robot.clickOn("#oldpassField").write("oldpassword");
        robot.clickOn("#newpassField").write("newpassword");
        robot.clickOn("#changeBtn");
        Text profileErrorText = robot.lookup("#profileErrorText").queryAs(Text.class);
        assertEquals("Password succesfully changed", profileErrorText.getText());
    }

    /**
     * Test method to verify behavior when the incorrect current password is entered.
     * It simulates user interactions for changing the password with an incorrect
     * current password and asserts the error message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void testWrongPasswordBeforeChange(FxRobot robot) {
        robot.clickOn("#oldpassField").write("wrongpassword");
        robot.clickOn("#newpassField").write("newpassword");
        robot.clickOn("#changeBtn");
        Text profileErrorText = robot.lookup("#profileErrorText").queryAs(Text.class);
        assertEquals("Password incorrect", profileErrorText.getText());
    }

    /**
     * Test method to verify validation of new password length. It simulates user
     * interactions for changing the password with a short new password and asserts
     * the error message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
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
