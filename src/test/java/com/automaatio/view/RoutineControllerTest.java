package com.automaatio.view;

import com.automaatio.controller.mainpage.RoutineController;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.CurrentLocale;
import com.dlsc.gemsfx.TimePicker;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        System.out.println("dev " + testDevice.getDeviceID());
        cache.setDevice(testDevice);
    }

    private void getFields(FxRobot robot) {
        statusMessage = robot.lookup("#errorMessageField").queryAs(Label.class);
    }

    private static void setupRoutines() {
        System.out.println("curret locale: " + (new CurrentLocale()).getCurrentLocale());
        WeekdayDAO  weekdayDAO = new WeekdayDAO();
        EventTimeDAO eventTimeDAO = new EventTimeDAO();
        RoutineDAO routineDAO = new RoutineDAO();
        List<Weekday> weekdays = weekdayDAO.getAll();
        Weekday friday = weekdays.get(4);
        Weekday sunday = weekdays.get(6);
        System.out.println("weekdays " + friday + sunday);
        EventTime routine1Time = eventTimeDAO.addAndReturnObject(new EventTime(LocalDateTime.of(2023, 11, 27, 14, 00),
                LocalDateTime.of(2023, 11, 27, 14, 10), friday));
        EventTime routine2Time = eventTimeDAO.addAndReturnObject(new EventTime(LocalDateTime.of(2023, 11, 27, 14, 00),
                LocalDateTime.of(2023, 11, 27, 16, 10), sunday));
        routine1 = routineDAO.addAndReturnObject(new Routine(testUser, testDevice, null, routine1Time, true));
        routine2 = routineDAO.addAndReturnObject(new Routine(testUser, testDevice, null, routine1Time, true));
    }

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

    @AfterAll
    static void endTest() {
        if (userDAO.getObject(testUser.getUsername()) != null) {
            userDAO.deleteObject(testUser.getUsername());
        }
    }
}