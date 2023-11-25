package view;

import com.automaatio.controller.mainpage.ProfileController;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

public class PassChangeTest extends ApplicationTest {

    private User user = new User("testaaja", "Testaaja", "Test", "0505551122", "testi@testi.fi", "oldpassword", 20, 1);
    UserDAO userDAO = new UserDAO();
    ProfileController profileController;
    CacheSingleton cache = CacheSingleton.getInstance();
    @Override
    public void start(Stage stage) throws Exception {
        cache.setUser(userDAO.getObject("niknoss"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/user-profile.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @Test
    void testPasswordChange(FxRobot robot) {
        robot.clickOn("#oldpassField").write("salasana1");
        robot.clickOn("#newpassField").write("newpassword");
        robot.clickOn("#changeBtn");
        verifyThat("#profileErrorText", hasText("Salasana vaihdettu"));
    }
}
