package com.automaatio.controller.mainpage;

import com.automaatio.components.localeSelector.LocaleSelector;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.NavigationUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the user profile
 * @author Matleena Kankaanpää
 * @author Nikita Nossenko
 * 8.9.2023
 */

public class ProfileController implements Initializable, IController{

    private Timeline notificationTimeline;

    @FXML
    private TextField fnameField, lnameField, bdayField, emailField, phoneField, priceLimit;
    @FXML
    Label usernameText;
    @FXML
    private PasswordField oldpassField, newpassField;
    @FXML
    Button changeBtn, saveMaxValue;
    @FXML
    private Text profileErrorText;
    @FXML
    private GridPane languageGrid;

    private UserDAO userDAO = new UserDAO();

    private User user = CacheSingleton.getInstance().getUser();
    private CacheSingleton cache = CacheSingleton.getInstance();

    private String loggedInUsername = user.getUsername();
    private String loggedInName = user.getFirstName();
    private LocaleSelector localeSelector = new LocaleSelector();
    private ResourceBundle resourceBundle;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        usernameText.setText(loggedInUsername);
        fnameField.setText(loggedInName);
        lnameField.setText(user.getLastName());
        bdayField.setText(String.valueOf(user.getAge()));
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhoneNumber());
        priceLimit.setText(Double.toString(user.getMaxPrice()));
        languageGrid.add(localeSelector.getComboBox(),2,0);
    }

    @FXML
    private void onChangePasswordClick() {
        String oldPass = oldpassField.getText();
        String newPass = newpassField.getText();

        if (newPass.isEmpty() || newPass.length() < 8) {
            profileErrorText.setText(resourceBundle.getString("newPassEmpty"));
            return;
        }

        if (BCrypt.checkpw(oldPass, user.getPassword())) {
            String newHashedPass = BCrypt.hashpw(newPass, BCrypt.gensalt());
            user.setPassword(newHashedPass);
            userDAO.updatePassword(loggedInUsername, oldPass, newPass);

            oldpassField.clear();
            newpassField.clear();

            profileErrorText.setText(resourceBundle.getString("passChangedText"));
            System.out.println("Salasana vaihdettu");

            if (notificationTimeline != null) {
                notificationTimeline.stop();
            }
            notificationTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(3), event -> {
                        profileErrorText.setText("");
                    })
            );
            notificationTimeline.play();
        } else {
            System.out.println("Salasana väärin");
            profileErrorText.setText(resourceBundle.getString("wrongPasswordTxt"));

            if (notificationTimeline != null) {
                notificationTimeline.stop();
            }
            notificationTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(3), event -> {
                        profileErrorText.setText(""); // Clear the message
                    })
            );
            notificationTimeline.play();
        }
    }

    @FXML
    public void onMaxValueSave(){
        double price = Double.parseDouble(priceLimit.getText());
        try {
            userDAO.updateMaxPrice(price, user.getUsername());
            user.setMaxPrice(price);
            cache.setUser(user);
        } catch (Exception e) {
            System.out.println("Ongelma hinnan päivittämisessä.");
        }

    }

    public void setUser(User user) {
        this.user = user;
    }
}