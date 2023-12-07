package com.automaatio.view;

import com.automaatio.controller.mainpage.RoutineController;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.dlsc.gemsfx.TimePicker;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code RoutineControllerTest} class contains TestFX-based UI tests for
 * the routine management functionality in the application. It uses {@link FxRobot} to
 * simulate user interactions with the UI.
 * <p>
 * This test class is designed to ensure that the functionality for creating, editing,
 * and deleting routines works as expected. It includes tests for adding a new routine,
 * editing an existing routine, and deleting a routine.
 */
@ExtendWith(ApplicationExtension.class)
class RoutineControllerTest {
    private static final CacheSingleton cache = CacheSingleton.getInstance();
    private static final UserDAO userDAO = new UserDAO();
    private static final DeviceDAO deviceDAO = new DeviceDAO();
    private static final DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private Label statusMessage;
    private static User testUser;
    private static Device testDevice;
    private static DeviceGroup testDeviceGroup;
    private static Routine routine1, routine2;

    // Create a test user into the database and save in the cache
    private static void setupUser() {
        String testUsername = "testuser-mk-3";

        if (userDAO.getObject(testUsername) == null) {
            testUser = userDAO.addAndReturnObject(new User(testUsername, "test", "user", "123456789", "test@email.com", "password123", 0, 1));
        } else {
            testUser = userDAO.getObject(testUsername);
        }
        testUser.setLocale("en_US");
        cache.setUser(testUser);
    }

    /*
    Create a test device (+ device group) into the database and
    save the device in the cache
     */
    private static void setupDevice() {
        User user = cache.getUser();
        testDeviceGroup = deviceGroupDAO.addAndReturnObject(new DeviceGroup("TestGroup", user));
        testDevice = deviceDAO.addAndReturnObject(new Device(0, "Roomba", "0", testDeviceGroup, cache.getUser().getUsername()));
        cache.setDevice(testDevice);
    }

    private void getFields(FxRobot robot) {
        statusMessage = robot.lookup("#errorMessageField").queryAs(Label.class);
    }

    private static void setupRoutines() {
        EventTimeDAO eventTimeDAO = new EventTimeDAO();
        RoutineDAO routineDAO = new RoutineDAO();
        List<Weekday> weekdays = new WeekdayDAO().getAll();
        EventTime routine1Time = eventTimeDAO.addAndReturnObject(new EventTime(LocalDateTime.of(2023, 11, 27, 14, 00),
                LocalDateTime.of(2023, 11, 27, 16, 10), weekdays.get(4)));
        EventTime routine2Time = eventTimeDAO.addAndReturnObject(new EventTime(LocalDateTime.of(2023, 11, 27, 21, 06),
                LocalDateTime.of(2023, 11, 27, 21, 36), weekdays.get(6)));
        routine1 = routineDAO.addAndReturnObject(new Routine(testUser, testDevice, null, routine1Time, true));
        routine2 = routineDAO.addAndReturnObject(new Routine(testUser, testDevice, null, routine2Time, true));
    }

    /**
     * Initializes the JavaFX environment for the test and sets up the necessary data
     * for testing routines. This method is called before each test execution to set up
     * the UI components and test data.
     *
     * @param stage The primary stage for this application.
     * @throws IOException if there is an error during setup.
     */
    @Start
    private void start(Stage stage) throws IOException {
        setupUser();
        setupDevice();
        setupRoutines();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(RoutineController.class.getResource("/view/routine.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test method to verify the functionality of editing a routine. It simulates user
     * interactions for modifying an existing routine and asserts the success message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void editRoutineTest(FxRobot robot) {
        getFields(robot);
        List<Button> editButtons = new ArrayList<>(robot.lookup(".edit-icon-button").queryAllAs(Button.class));
        robot.clickOn(editButtons.get(0));

        List<TimePicker> startTimePickers = new ArrayList<>(robot.lookup(".timepicker-start").queryAllAs(TimePicker.class));
        TimePicker startTimePicker = startTimePickers.get(1);
        List<TimePicker> endTimePickers = new ArrayList<>(robot.lookup(".timepicker-end").queryAllAs(TimePicker.class));
        TimePicker endTimePicker = endTimePickers.get(1);

        robot.clickOn(startTimePicker);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.clickOn(startTimePicker);
        robot.clickOn(endTimePicker);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        List<Button> saveButtons = new ArrayList<>(robot.lookup(".save-icon-button").queryAllAs(Button.class));
        robot.clickOn(saveButtons.get(0));
        assertEquals("Routine updated successfully", statusMessage.getText());
    }

    /**
     * Test method to verify the functionality of deleting a routine. It simulates user
     * interactions for removing an existing routine and asserts the success message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void deleteRoutineTest(FxRobot robot) {
        getFields(robot);
        List<Button> editButtons = new ArrayList<>(robot.lookup(".edit-icon-button").queryAllAs(Button.class));
        robot.clickOn(editButtons.get(1));
        List<Button> deleteButtons = new ArrayList<>(robot.lookup(".delete-icon-button").queryAllAs(Button.class));
        robot.clickOn(deleteButtons.get(1));
        robot.clickOn(robot.lookup(".confirm-delete-button").queryAs(Button.class));
        assertEquals("Routine deleted successfully", statusMessage.getText());
    }

    /**
     * Test method to verify the functionality of adding a new routine. It simulates user
     * interactions for creating a new routine and asserts the success message.
     *
     * @param robot The {@link FxRobot} instance used to simulate user interactions.
     */
    @Test
    void addRoutineTest(FxRobot robot) {
        getFields(robot);
        robot.clickOn("#addRoutineButton");
        TimePicker startTimePicker = robot.lookup(".timepicker-start").queryAs(TimePicker.class);
        TimePicker endTimePicker = robot.lookup(".timepicker-end").queryAs(TimePicker.class);

        robot.clickOn(endTimePicker);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.clickOn(endTimePicker);

        Set<CheckBox> checkboxes = robot.lookup(".weekday-checkbox").queryAllAs(CheckBox.class);
        robot.clickOn(checkboxes.stream().filter(checkbox->checkbox.getText().equals("Saturday")).findFirst().get());
        robot.clickOn(robot.lookup("#saveButton").queryAs(Button.class));
        assertEquals("Routine saved successfully", statusMessage.getText());
    }

    /**
     * Teardown method to clean up after all tests. It removes any test data created
     * during the tests to ensure a clean state for subsequent tests.
     */
    @AfterAll
    static void endTest() {
        if (userDAO.getObject(testUser.getUsername()) != null) {
            userDAO.deleteObject(testUser.getUsername());
        }
    }
}