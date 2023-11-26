package view;

import com.automaatio.controller.mainpage.ProfileController;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class PassChangeTest extends ApplicationTest {

    //private User user = new User("testaaja", "Testaaja", "Test", "0505551122", "testi@testi.fi", "oldpassword", 20, 1);
    UserDAO userDAO = new UserDAO();
    CacheSingleton cache = CacheSingleton.getInstance();
    @Start
    public void start(Stage stage) throws Exception {
        cache.setUser(userDAO.getObject("niknoss"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("/view/user-profile.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @Test
    void testPasswordChange(FxRobot robot) {
        robot.clickOn("#oldpassField").write("newpassword");
        robot.clickOn("#newpassField").write("salasana1");
        robot.clickOn("#changeBtn");
        Text profileErrorText = robot.lookup("#profileErrorText").queryAs(Text.class);
        assertEquals("Password incorrect", profileErrorText.getText());
    }
}
