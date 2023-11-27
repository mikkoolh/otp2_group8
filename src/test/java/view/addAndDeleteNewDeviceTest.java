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

@ExtendWith(ApplicationExtension.class)
public class addAndDeleteNewDeviceTest {
    private static UserDAO userDAO = new UserDAO();
    private static DeviceDAO deviceDAO = new DeviceDAO();

    @AfterEach
    void tearDownEach() {
        deviceDAO.deleteDevicesByUsername("testuser");
    }

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

    @Start
    private void start(Stage stage) throws IOException {
        URL url = GraphicalUI.class.getResource("/view/menu/devices.fxml");
        System.out.println("FXML URL: " + url);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/menu/devices.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void test(FxRobot robot) throws InterruptedException {
        robot.clickOn("#deviceNameField").write("test device");
        robot.clickOn("#addButton");

        sleep(2000);

        Device testDevice = deviceDAO.getObject("test device");
        Assertions.assertNotNull(testDevice, "Laitetta 'test device' ei löydy tietokannasta");
    }
}
