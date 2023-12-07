package view;

import com.automaatio.model.database.*;
import com.automaatio.view.GraphicalUI;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.model.database.User;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

/**
 * The {@code AddAndDeleteNewRoomTest} class contains TestFX-based UI tests for
 * adding and deleting a new room in the application. It uses {@link FxRobot} to
 * simulate user interactions with the UI.
 *
 * This test class is designed to ensure that the functionality for adding a new room
 * works as expected and that the room is correctly added to the database. It also
 * includes setup and teardown methods to prepare and clean up the testing environment.
 */
@ExtendWith(ApplicationExtension.class)
public class AddAndDeleteNewRoomTest {
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private static UserDAO userDAO = new UserDAO();

    /**
     * Setup method to prepare the testing environment. It ensures a test user is
     * available and sets up the necessary environment in the {@link CacheSingleton}.
     */
    @BeforeAll
    static void setup() {
        CacheSingleton cache = CacheSingleton.getInstance();
        User user;

        if (userDAO.getObject("testuser") == null) {
            user = new User("testuser", "test", "user", "123456789", "test@email.com", "password123", 0, 1);
            user.setLocale("en_US");
            cache.setUser(user);
            System.out.println("user: " + cache.getUser().getUsername());
        } else {
            user = userDAO.getObject("testuser");
            cache.setUser(user);
        }
    }

    /**
     * Teardown method to clean up after each test. It removes any rooms added
     * by the test user to ensure a clean state for subsequent tests.
     */
    @AfterEach
    void tearDownEach() {
        deviceGroupDAO.deleteGroupsByUsername("testuser");
    }

    /**
     * Initializes the JavaFX environment for the test. This method is called
     * before each test execution to set up the UI components.
     *
     * @param stage The primary stage for this application.
     * @throws IOException if loading the FXML file fails.
     */
    @Start
    private void start(Stage stage) throws IOException {
        URL url = GraphicalUI.class.getResource("/view/menu/rooms.fxml");
        System.out.println("FXML URL: " + url);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/menu/rooms.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test method to verify the addition of a new room. It simulates user
     * interactions for adding a room and asserts that the room is correctly
     * added to the database.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    @Test
    void test(FxRobot robot) throws InterruptedException {
        robot.clickOn("#newRoomTextField").write("testroom");
        robot.clickOn("#addBtn");

        sleep(2000);

        User user = userDAO.getObject("testuser");
        DeviceGroup addedRoom = deviceGroupDAO.findRoomByName("testroom", user);
        Assertions.assertNotNull(addedRoom, "Huonetta 'testroom' ei löydy tietokannasta");
    }
}
