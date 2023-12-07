package com.automaatio.controller.mainpage.menu;

import com.automaatio.model.ElectricityPriceConnector;
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
/**
 * The ProfileController class is responsible for managing the user profile display and interactions in the HomeAutomation application.
 * It implements the Initializable interface to handle initialization of the controller and the Menu interface to define menu-related functionality.
 * The controller is associated with an FXML file representing the UI for user profile information.
 * Users can view their username, name, selected profile picture, and electricity price information.
 * The class provides methods for updating the displayed profile picture and opening the user profile page.
 * It also allows users to log out and change their profile picture by invoking a popup window.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
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

    private ElectricityPriceConnector elConnect;
    private ResourceBundle resourceBundle;

    private int selectedPic = cache.getUser().getSelectedPicture();

    /**
     * Constructor for the ProfileController.
     * Initializes the mainPane with the instance from the cache.
     */
    public ProfileController() {
        mainPane = cache.getMainPane();
    }

    /**
     * Initializes the controller and sets the resourceBundle.
     *
     * @param location  The URL location of the FXML file.
     * @param resources The ResourceBundle containing localized resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        show();
        loadProfilePic();
    }

    /**
     * Updates the selected profile picture and loads it.
     *
     * @param selectedPic The identifier for the selected profile picture.
     */
    public void updateProfilePic(int selectedPic) {
        this.selectedPic = selectedPic;
        loadProfilePic();
    }

    /**
     * Loads and displays the profile picture based on the selected picture identifier.
     */
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

    /**
     * Displays the user profile information.
     */
    @Override
    public void show() {
        usernameTXT.setText(username);
        nameTXT.setText(resourceBundle.getString("greetingTxt") + name + "!");
        loadProfilePic();

        try {
            elConnect = new ElectricityPriceConnector();
            electricityPrice.setText(resourceBundle.getString("elPriceDefaultTxt") + elConnect.getElPrice()+ resourceBundle.getString("elPriceUnitsTxt"));
        } catch (Exception e) {
            System.out.println("Ongelma sähkönhinnan lataamisessa: " + e);
        }

    }
    /**
     * Opens the user profile page.
     */
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

    /**
     * Handles the click action when the "Logout" button is clicked.
     * Logs out the user and navigates to the login page.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O exception occurs.
     */
    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        cache.setUser(null);
        cache.setDirection(NodeOrientation.LEFT_TO_RIGHT);
        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }

    /**
     * Handles the click action when the "Change Picture" button is clicked.
     * Opens a popup window for selecting a new profile picture.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O exception occurs.
     */
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
