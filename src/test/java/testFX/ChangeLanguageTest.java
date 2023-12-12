package testFX;


import com.automaatio.components.localeSelector.LocaleItem;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code ChangeLanguageTest} class extends {@code AbstractTestFX} and contains TestFX-based UI tests
 * for the language change functionality in the application. It uses {@link FxRobot} to simulate user
 * interactions with the UI.
 *
 * This test class is designed to ensure that the functionality for changing the application's language
 * works as expected. It includes tests for changing the language to English, Finnish, Russian, and Arabic.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class ChangeLanguageTest extends AbstractTestFX {
    final private String username = "testFX", passwd = "testfx123", userFieldId = "#usernameField",
            passwdFld = ".password-field", loginBtnId = "#loginButton", welcomeTxtId = "#welcomeMain",
            profilePaneId = "#profileAccordion", nameTxtId = "#nameTXT", editProfId = "#editProfAccordion",
            profileLabelId = "#usernameText", langBoxId = "#languageBox", langTxtId = "#languageText",
            eng = "en_US", fin = "fi_FI", rus = "ru_RU", ar = "ar_SA";
    private UserDAO userDAO;
    private TextField userField;
    private Text welcomeTxt, nameTxt, langTxt;
    private Label userLabel;
    private TitledPane profPane;
    private ComboBox langBox;
    private TextInputControl passwdField;
    private Button loginBtn, editProfBtn;

    /**
     * Test method to verify the functionality of changing the application's language to English.
     * It simulates user interactions for selecting English from the language dropdown and asserts
     * the success of the language change.
     *
     * @param robo The {@link FxRobot} instance used to simulate user interactions.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    @Test
    void changeLangToEng(FxRobot robo) throws InterruptedException {
        getLoginFields(robo);
        loginToApp(robo);
        goToProfile(robo);
        changeLangTo(robo, eng);
    }

    /**
     * Test method to verify the functionality of changing the application's language to Finnish.
     * It simulates user interactions for selecting Finnish from the language dropdown and asserts
     * the success of the language change.
     *
     * @param robo The {@link FxRobot} instance used to simulate user interactions.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    @Test
    void changeLangToFin(FxRobot robo) throws InterruptedException {
        getLoginFields(robo);
        loginToApp(robo);
        goToProfile(robo);
        changeLangTo(robo, fin);
    }

    /**
     * Test method to verify the functionality of changing the application's language to Russian.
     * It simulates user interactions for selecting Russian from the language dropdown and asserts
     * the success of the language change.
     *
     * @param robo The {@link FxRobot} instance used to simulate user interactions.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    @Test
    void changeLangToRus(FxRobot robo) throws InterruptedException {
        getLoginFields(robo);
        loginToApp(robo);
        goToProfile(robo);
        changeLangTo(robo, rus);
    }

    /**
     * Test method to verify the functionality of changing the application's language to Arabic.
     * It simulates user interactions for selecting Arabic from the language dropdown and asserts
     * the success of the language change.
     *
     * @param robo The {@link FxRobot} instance used to simulate user interactions.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    @Test
    void changeLangToAr(FxRobot robo) throws InterruptedException {
        getLoginFields(robo);
        loginToApp(robo);
        goToProfile(robo);
        changeLangTo(robo, ar);
    }

    private void getLoginFields(FxRobot robot) {
        userField = robot.lookup(userFieldId).queryAs(TextField.class);
        passwdField = robot.lookup(passwdFld).queryTextInputControl();
        loginBtn = robot.lookup(loginBtnId).queryButton();
    }

    private void loginToApp(FxRobot robo) {
        robo.clickOn(userField).write(username);
        robo.clickOn(passwdField).write(passwd);
        robo.clickOn(loginBtn);
        WaitForAsyncUtils.waitForFxEvents();
        findMainFields(robo);
    }

    private void findMainFields(FxRobot robo) {
        welcomeTxt = robo.lookup(welcomeTxtId).queryText();
        profPane = robo.lookup(profilePaneId).queryAs(TitledPane.class);
        assertEquals(true, welcomeTxt.isVisible(), "No mainpage");
    }

    private void goToProfile(FxRobot robo) {
        robo.clickOn(profPane);
        WaitForAsyncUtils.waitForFxEvents();
        nameTxt = robo.lookup(nameTxtId).queryText();
        editProfBtn = robo.lookup(editProfId).queryButton();
        assertEquals(nameTxt.isVisible(), true, "Profile accordion not open");
        robo.clickOn(editProfBtn);
        WaitForAsyncUtils.waitForFxEvents();
        userLabel = robo.lookup(profileLabelId).queryAs(Label.class);
        assertEquals(true, userLabel.isVisible(), "Failed to go to Edit profile");
    }

    private void changeLangTo(FxRobot robo, String lang) throws InterruptedException {
        userDAO = new UserDAO();
        int index;
        if (lang.equals(fin)) {
            index = 0;
        } else if (lang.equals(eng)) {
            index = 1;
        } else if (lang.equals(rus)) {
            index = 2;
        } else if (lang.equals(ar)) {
            index = 3;
        } else {
            index = -1;
        }

        langBox = robo.lookup(langBoxId).queryComboBox();
        robo.clickOn(langBox);

        interact(() -> {
            langBox.getSelectionModel().select(index);
        });

        WaitForAsyncUtils.waitForFxEvents();
        langTxt = robo.lookup(langTxtId).queryText();

        String dBlocale = userDAO.getLocale(username).toString();
        System.out.println("\n" + lang + " equals " + dBlocale + "\n");
        assertEquals(lang, dBlocale, "Wrong locale in database");

        if (lang.equals(fin)) {
            assertEquals("Kieli", langTxt.getText(), "Language selection failed");
        } else if (lang.equals(eng)) {
            assertEquals("Language", langTxt.getText(), "Language selection failed");
        } else if (lang.equals(rus)) {
            assertEquals("Язык", langTxt.getText(), "Language selection failed");
        } else if (lang.equals(ar)) {
            assertEquals("اللغة", langTxt.getText(), "Language selection failed");
        }
    }
}