package com.automaatio.view;

import com.automaatio.controller.mainpage.RoutineController;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.dlsc.gemsfx.TimePicker;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

@ExtendWith(ApplicationExtension.class)

class RoutineControllerTest {
    private static final CacheSingleton cache = CacheSingleton.getInstance();
    private static final UserDAO userDAO = new UserDAO();
    private static final DeviceDAO deviceDAO = new DeviceDAO();
    private static User testUser;
    private static Device testDevice;
    private static DeviceGroup testDeviceGroup;

    @BeforeAll
    static void setup() {
        setupUser();
        setupDevice();
        System.out.println("user in cache: " + cache.getUser().getUsername());
        System.out.println("device in cache: " + cache.getDevice());
    }

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
        testDeviceGroup = (new DeviceGroupDAO()).addAndReturnObject(new DeviceGroup("TestGroup", user));
        testDevice = deviceDAO.addAndReturnObject(new Device(0, "Roomba", "0", testDeviceGroup, cache.getUser().getUsername()));
        System.out.println("dev " + testDevice.getDeviceID());
        cache.setDevice(testDevice);
    }

    private static void setupRoutines() {
        Routine routine1 = new Routine();
    }

    @Start
    private void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("en", "US"));
        FXMLLoader fxmlLoader = new FXMLLoader(RoutineController.class.getResource("/view/routine.fxml"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void addRoutineTest(FxRobot robot) {
        robot.clickOn("#addRoutineButton");

        TimePicker startTimePicker = robot.lookup(".timepicker-start").queryAs(TimePicker.class);
        TimePicker endTimePicker = robot.lookup(".timepicker-end").queryAs(TimePicker.class);

        System.out.println("now " +LocalTime.now().getHour());

        /*
        Was unable to type in the field or target the spinny thing so we're going with what we have here
         */
        robot.clickOn(startTimePicker);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);

        robot.clickOn(endTimePicker);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.UP).release(KeyCode.UP);

        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn(startTimePicker);

        Set<CheckBox> checkboxes = robot.lookup(".weekday-checkbox").queryAllAs(CheckBox.class);
        System.out.println(checkboxes);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("beep boop");
    }

    @AfterAll
    static void endTest() {
        if (userDAO.getObject(testUser.getUsername()) != null) {
            userDAO.deleteObject(testUser.getUsername());
        }

        List<Device> testDevices = deviceDAO.getDevicesByUserName(testUser.getUsername());

        for (Device device : testDevices) {
            System.out.println("test " + device.getName());
        }
    }
}