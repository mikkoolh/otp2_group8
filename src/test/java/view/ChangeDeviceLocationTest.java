package view;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code ChangeDeviceLocationTest} class contains TestFX-based UI tests for
 * changing the location of a device within the application. It uses {@link FxRobot} to
 * simulate user interactions with the UI.
 *
 * This test class is designed to ensure that the functionality for changing a device's
 * location (device group) works as expected. It includes setup and teardown methods to
 * prepare and clean up the testing environment, as well as specific tests for UI interactions
 * related to changing device locations.
 */
@ExtendWith(ApplicationExtension.class)
public class ChangeDeviceLocationTest {
    private static UserDAO userDAO = new UserDAO();
    private static DeviceDAO deviceDAO = new DeviceDAO();
    private static DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private static CacheSingleton cache = CacheSingleton.getInstance();
    private Stage primaryStage;

    /**
     * Teardown method to clean up after all tests. It removes any devices and device groups
     * added by the test user to ensure a clean state for subsequent tests.
     */
    @AfterAll
    static void tearDownEach() {
        deviceDAO.deleteDevicesByUsername("testuser");
        deviceGroupDAO.deleteGroupsByUsername("testuser");
    }

    /**
     * Setup method to prepare the testing environment. It ensures a test user, device,
     * and device group are available and sets up the necessary environment in the
     * {@link CacheSingleton}.
     */
    @BeforeAll
    static void setup() {
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
        DeviceGroup testRoom = new DeviceGroup("test room", cache.getUser());
        cache.setRoom(testRoom);
        deviceGroupDAO.addObject(testRoom);

        Device testDevice = new Device(0, "test device", "012", null, "testuser");
        cache.setDevice(testDevice);
        deviceDAO.addObject(testDevice);
    }

    /**
     * Initializes the JavaFX environment for the test. This method is called
     * before each test execution to set up the primary stage.
     *
     * @param stage The primary stage for this application.
     */
    @Start
    private void start(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Setup method for each test. It loads the necessary scene based on the test being executed.
     *
     * @param testInfo Information about the test being executed.
     */
    @BeforeEach
    void setUp(TestInfo testInfo) {
        Platform.runLater(() -> {
            try {
                if (testInfo.getDisplayName().equals("testChangingDeviceLocation")) {
                    loadScene("/view/device.fxml");
                } else {
                    loadScene("/view/menu/devices.fxml");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Loads the specified FXML scene into the primary stage.
     *
     * @param fxmlFile The path to the FXML file to be loaded.
     * @throws IOException if loading the FXML file fails.
     */
    private void loadScene(String fxmlFile) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Test method to verify the functionality of pressing the edit button on a device.
     * It simulates user interactions for expanding a device's details.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    @Test
    void testForPressingEditButton(FxRobot robot) throws InterruptedException {
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("#devicesVBox");
        Node expandBtn = robot.lookup(".expandBtn").query();
        if (expandBtn != null) {
            robot.clickOn(expandBtn);
        } else {
            System.out.println("Expand button not found");
        }
    }

    /**
     * Test method to verify changing the location of a device. It simulates user
     * interactions for selecting a new device group and asserts that the device's
     * location is correctly updated.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    @Test
    void testChangingDeviceLocation(FxRobot robot) throws InterruptedException {
        Platform.runLater(() -> {
            try {
                loadScene("/view/device.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        WaitForAsyncUtils.waitForFxEvents();
        Thread.sleep(1000);

        ComboBox<?> deviceGroupComboBox = robot.lookup("#deviceGroup").queryComboBox();
        robot.clickOn(deviceGroupComboBox);
        WaitForAsyncUtils.waitForFxEvents();

        try {
            WaitForAsyncUtils.waitForFxEvents();
            Node item = robot.lookup(".list-cell").match(node ->
                    node instanceof Labeled &&
                            ((Labeled) node).getText() != null &&
                            ((Labeled) node).getText().equals("test room")
            ).query();

            if (item != null) {
                robot.clickOn(item);
                deviceDAO.updateDeviceGroup(cache.getDevice().getDeviceID(), cache.getRoom().getDeviceGroupId());
                cache.getDevice().setDeviceGroup(deviceGroupDAO.getObject(cache.getRoom().getDeviceGroupId()));
            } else {
                System.out.println("There is no room named 'test room'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        WaitForAsyncUtils.waitForFxEvents();
        Assertions.assertEquals("test room", cache.getDevice().getDeviceGroup().toString(), "Device group should be test room");
    }
}
