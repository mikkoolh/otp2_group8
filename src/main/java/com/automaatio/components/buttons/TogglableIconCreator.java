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
 *
 * The {@code TogglableIconCreator} class is responsible for creating a custom button
 * with two different image states. These states toggle upon each click on the button.
 * The class also supports tooltips for each state to provide additional information.
 *
 * This class is abstract and designed to be extended for specific implementations
 * where toggling icons on a button is required.
 *
 */
public abstract class TogglableIconCreator {
    protected Button button;
    protected ImageView defaultView, altView;
    private Tooltip defaultTooltip, altTooltip;
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", new Locale("fi", "FI"));

    /**
     * Creates a button with two images and corresponding tooltips. The images and tooltips
     * toggle when the button is clicked.
     *
     * @param defaultImage The URL of the image to be used as the default view.
     * @param altImage The URL of the image to be used as the alternate view.
     * @param defaultTooltipText The text for the tooltip in the default state.
     * @param altTooltipText The text for the tooltip in the alternate state.
     * @return A {@link Button} with the specified images and tooltips, ready for use in a UI.
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
