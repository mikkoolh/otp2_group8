package com.automaatio.components.buttons;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Matleena Kankaanpää
 * 7.10.2023
 *
 * Image button
 */

public abstract class IconCreator {
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

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
