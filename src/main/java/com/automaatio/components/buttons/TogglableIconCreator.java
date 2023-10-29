package com.automaatio.components.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Matleena Kankaanpää
 * 7.10.2023
 *
 * A class for creating buttons with two image
 * options that swap when clicked.
 */

public abstract class TogglableIconCreator {
    protected Button button;
    protected ImageView defaultView, altView;
    private Tooltip defaultTooltip, altTooltip;
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

    public Button create(String defaultImage, String altImage, String defaultTooltipText, String altTooltipText) {
        button = new Button();
        defaultView = new ImageView(new Image(defaultImage));
        altView = new ImageView(new Image(altImage));
        defaultTooltip = new Tooltip(defaultTooltipText);
        altTooltip = new Tooltip(altTooltipText);
        defaultView.setPreserveRatio(true);
        altView.setPreserveRatio(true);
        int icon_size = 18; // Icon dimensions
        defaultView.setFitHeight(icon_size);
        altView.setFitHeight(icon_size);
        button.setGraphic(defaultView);
        button.setTooltip(defaultTooltip);
        button.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                if (button.getGraphic() == defaultView) {
                    button.setGraphic(altView);
                    button.setTooltip(altTooltip);
                } else {
                    button.setGraphic(defaultView);
                    button.setTooltip(defaultTooltip);
                }
            }
        });
        return button;
    }
}
