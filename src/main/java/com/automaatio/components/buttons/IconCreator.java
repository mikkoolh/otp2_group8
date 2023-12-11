package com.automaatio.components.buttons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The abstract IconCreator defines a common interface for icon buttons.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */
public abstract class IconCreator {
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

    /**
     * Class constructor
     *
     * @param image     The url of the image to be displayed on the button
     * @param tooltip   The tooltip text to be shown when hovering over the button
     * @return          An image button with a tooltip text
     */
    public Button create(String image, String tooltip) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(image));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(18);
        button.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
        button.setGraphic(imageView);
        button.setTooltip((new Tooltip(tooltip)));
        button.getStyleClass().clear();
        button.setStyle("-fx-border-color: #5E5E5E; -fx-border-radius: 3;");
        button.setPadding(new Insets(3));
        return button;
    }
}
