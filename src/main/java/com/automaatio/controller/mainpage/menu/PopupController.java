package com.automaatio.controller.mainpage.menu;

import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupController implements Initializable{
    @FXML
    private ImageView male, female, anime, hacker, detective, defaultpic;
    @FXML
    private Button saveBtn, exitBtn;
    private int selectedPic = -1;

    CacheSingleton cache = CacheSingleton.getInstance();

    private ProfileController profileController;

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    @FXML
    private void onSaveClick() {
        if (selectedPic != -1) {
            User user = cache.getUser();
            UserDAO userDAO = new UserDAO();
            userDAO.updatePicture(user.getUsername(), selectedPic);
            profileController.updateProfilePic(selectedPic);
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("No image selected");
        }
    }

    @FXML
    private void onExitClick() {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        male.setImage(new Image("images/male.jpg"));
        female.setImage(new Image("images/female.jpg"));
        anime.setImage(new Image("images/anime.jpg"));
        hacker.setImage(new Image("images/hacker.jpg"));
        detective.setImage(new Image("images/detective.jpg"));
        defaultpic.setImage(new Image("images/profilepic.jpg"));

        male.setOnMouseClicked(event -> selectedPic = 1);
        female.setOnMouseClicked(event -> selectedPic = 2);
        anime.setOnMouseClicked(event -> selectedPic = 3);
        hacker.setOnMouseClicked(event -> selectedPic = 4);
        detective.setOnMouseClicked(event -> selectedPic = 5);
        defaultpic.setOnMouseClicked(event -> selectedPic = 6);
    }

    public void onImageClick(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView clickedImageView = (ImageView) mouseEvent.getSource();

        if (clickedImageView == male) {
            selectedPic = 1;
        } else if (clickedImageView == female) {
            selectedPic = 2;
        } else if (clickedImageView == anime) {
            selectedPic = 3;
        } else if (clickedImageView == hacker) {
            selectedPic = 4;
        } else if (clickedImageView == detective) {
            selectedPic = 5;
        } else if (clickedImageView == defaultpic) {
            selectedPic = 6;
        }
    }
}
