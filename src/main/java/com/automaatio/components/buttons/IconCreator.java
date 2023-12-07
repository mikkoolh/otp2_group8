package com.automaatio.components.buttons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Matleena Kankaanpää
 *
 * The {@code IconCreator} class is an abstract class designed to create customized
 * buttons with an icon and a tooltip. It serves as a base class for creating various
 * types of icon-based buttons.
 *
 * This class provides a common implementation for creating a button with a specified
 * image and tooltip text. The appearance and behavior of the button can be further
 * customized in subclasses.
 */
public abstract class IconCreator {
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

    /**
     * Creates a button with the specified image and tooltip text.
     * The button is styled with a specific appearance and includes an image view
     * as its graphic component.
     *
     * @param image The path to the image file to be used as the button's icon.
     * @param tooltip The text to be displayed in the button's tooltip.
     * @return A styled {@link Button} with an icon and a tooltip.
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
