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
import org.testfx.util.WaitForAsyncUtils;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Nikita Nossenko
 *
 * The {@code ProfilePicTest} class contains TestFX-based UI tests for
 * the profile picture change functionality in the application. It uses {@link FxRobot} to
 * simulate user interactions with the UI.
 *
 * This test class is designed to ensure that the functionality for changing a user's
 * profile picture works as expected. It includes tests for verifying the default profile
 * picture and changing to a new profile picture.
 */
@ExtendWith(ApplicationExtension.class)
public class ProfilePicTest extends ApplicationTest {
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
        userDAO.deleteObject("testaaja");
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
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("/view/menu/profile.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test method to verify the functionality of changing the profile picture.
     * It simulates user interactions for selecting and saving a new profile picture
     * and asserts the change in the user's profile picture.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void TestPicChange(FxRobot robot) {
        User user = CacheSingleton.getInstance().getUser();
        assertEquals(0, user.getSelectedPicture(), "Test user's selected picture should be 0 by default");
        robot.clickOn("#picBtn");
        robot.clickOn("#anime");
        robot.clickOn("#saveBtn");
        WaitForAsyncUtils.waitForFxEvents(3000);

        User updatedUser = userDAO.getObject(user.getUsername());
        System.out.println("Käyttäjän tiedot: " + updatedUser.getUsername() + ", käyttäjän valittu kuvaID: " + updatedUser.getSelectedPicture());
        assertEquals(3, updatedUser.getSelectedPicture(), "Test user's selected picture should be 3 after selecting 3rd pic option");
    }
}
