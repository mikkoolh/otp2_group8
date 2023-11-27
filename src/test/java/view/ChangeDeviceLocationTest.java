package view;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.view.GraphicalUI;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
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

@ExtendWith(ApplicationExtension.class)
public class ChangeDeviceLocationTest {
    private static UserDAO userDAO = new UserDAO();
    private static DeviceDAO deviceDAO = new DeviceDAO();
    private static DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private static CacheSingleton cache = CacheSingleton.getInstance();
    private Stage primaryStage;

    @AfterAll
    static void tearDownEach() {
        deviceDAO.deleteDevicesByUsername("testuser");
        deviceGroupDAO.deleteGroupsByUsername("testuser");
    }

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

        deviceGroupDAO.addObject(new DeviceGroup("test room", cache.getUser()));
        Device testDevice = new Device(0, "test device", "012", null, "testuser");
        deviceDAO.addObject(testDevice);
    }

    @Start
    private void start(Stage stage) {
        this.primaryStage = stage;
    }

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

    private void loadScene(String fxmlFile) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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
        Thread.sleep(2000);

        robot.clickOn("#usageData").write("2");
        //ComboBox deviceGroupComboBox = robot.lookup("#deviceGroup").queryComboBox();
        //robot.clickOn(deviceGroupComboBox);
    }
}
