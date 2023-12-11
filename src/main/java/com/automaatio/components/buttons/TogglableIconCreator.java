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
 * The abstract TogglableIconCreator class is used for creating specific
 * togglable buttons with alternating images and tooltip texts that
 * swap when the button is clicked.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public abstract class TogglableIconCreator {
    protected Button button;
    protected ImageView defaultView, altView;
    private Tooltip defaultTooltip, altTooltip;
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

    /**
     * Creates a togglable button with two alternate states, images and tooltip texts
     *
     * @param defaultImage          The default image to be displayed on the button
     * @param altImage              An alternate image
     * @param defaultTooltipText    The default tooltip text of the button
     * @param altTooltipText        An alternate tooltip text
     * @return                      A togglable button with two alternate states, images and tooltip texts
     */
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
