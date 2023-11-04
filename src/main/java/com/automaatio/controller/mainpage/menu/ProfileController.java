package com.automaatio.controller.mainpage.menu;

import com.automaatio.Main;
import com.automaatio.controller.mainpage.MainMenuController;
import com.automaatio.utils.ElectricityPriceConnector;
import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ProfileController implements Initializable, Menu {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private final Pane mainPane;

    private final String username = cache.getUser().getUsername(), name = cache.getUser().getFirstName();
    private BundleLoader bundleLoader = new BundleLoader();

    @FXML
    Text usernameTXT, nameTXT, electricityPrice;

    @FXML
    Button picBtn;

    @FXML
    ImageView profileView;

    private ResourceBundle resourceBundle;

    private int selectedPic = cache.getUser().getSelectedPicture();

    public ProfileController() {
        mainPane = cache.getMainPane();
        MainMenuController.setMenuProfileController(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resourceBundle = resources;
        show();
        loadProfilePic();
    }

    public void updateProfilePic(int selectedPic) {
        this.selectedPic = selectedPic;
        loadProfilePic();
    }

    private void loadProfilePic() {
        String imagePath;

        switch (selectedPic) {
            case 1:
                imagePath = "/images/male.jpg";
                break;
            case 2:
                imagePath = "/images/female.jpg";
                break;
            case 3:
                imagePath = "/images/anime.jpg";
                break;
            case 4:
                imagePath = "/images/hacker.jpg";
                break;
            case 5:
                imagePath = "/images/detective.jpg";
                break;
            case 6:
                imagePath = "/images/profilepic.jpg";
                break;
            default:
                imagePath = "/images/profilepic.jpg";
                break;
        }

        Image image = new Image(getClass().getResourceAsStream(imagePath));
        profileView.setImage(image);
    }

    @Override
    public void show() {
        usernameTXT.setText(username);
        nameTXT.setText(resourceBundle.getString("greetingTxt") + name + "!");
        loadProfilePic();

    }

    public void setElPriceNow(Double price){
       electricityPrice.setText(resourceBundle.getString("elPriceDefaultTxt") + String.format("%.2f", price) + resourceBundle.getString("elPriceUnitsTxt"));
    }

    public void openProfile() {
        System.out.println("open profile");
        try {
            resourceBundle = bundleLoader.loadResourceByUsersLocale();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user-profile.fxml"));
            loader.setResources(resourceBundle);
            Parent newView = loader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        cache.setUser(null);
        cache.setDirection(NodeOrientation.LEFT_TO_RIGHT);
        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }

    @FXML
    protected void onBtnClick(ActionEvent event) throws IOException {

        resourceBundle = bundleLoader.loadResourceByUsersLocale();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup.fxml"));
        loader.setResources(resourceBundle);
        Parent root = loader.load();
        MenuPopupController popupController = loader.getController();

        popupController.setProfileController(this);

        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Profile Picture Selection");

        Scene scene = new Scene(root);
        popupStage.setScene(scene);
        popupStage.showAndWait();

        loadProfilePic();
    }

}
