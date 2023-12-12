package com.automaatio.components;

import com.automaatio.components.buttons.ExpandButtonCreator;
import com.automaatio.components.buttons.ToggleButtonCreator;
import com.automaatio.components.buttons.DeleteButtonCreator;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;

/**
 * The {@code CreateVBoxColumn} class is responsible for creating a {@link VBox} layout
 * that represents a column in a user interface. This column typically contains a label,
 * and a set of buttons for actions like expand, delete, and toggle on/off.
 *
 * This class is designed to dynamically create the layout based on the type of object
 * provided (e.g., {@link Device} or {@link DeviceGroup}), adding appropriate buttons
 * and functionality.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class CreateVBoxColumn {
    private Button deleteBtn, expandBtn;
    private ToggleButton onOff;
    private Label label;
    private final int vBoxSpacing = 10, hBoxSpacing = 20;

    /**
     * Creates a {@link VBox} layout for a given object, with appropriate controls
     * and actions.
     *
     * @param object The object for which the VBox is being created. This can be a {@link Device}
     * or a {@link DeviceGroup}.
     * @param mainVBox The main {@link VBox} container in which this new VBox will be placed.
     * @param clickActions The {@link ClickActions} that define the actions to be performed
     * on button clicks.
     * @return A {@link VBox} containing a label and a set of action buttons.
     */
    public VBox create(Object object, VBox mainVBox, ClickActions clickActions) {
        VBox newDeviceVBox = new VBox(vBoxSpacing);
        label = createLabel(object.toString());
        deleteBtn = new DeleteButtonCreator(mainVBox, newDeviceVBox).create(object, clickActions);
        expandBtn = new ExpandButtonCreator().create(object, clickActions);

        if(object instanceof Device){
            onOff = new ToggleButtonCreator().create(object, clickActions);
        }

        HBox buttonsRow = new HBox(hBoxSpacing);
        buttonsRow.getChildren().addAll(expandBtn, deleteBtn, paneBuilder(10));
        HBox.setHgrow(buttonsRow.getChildren().get(buttonsRow.getChildren().size()-1), Priority.ALWAYS);

        if (object instanceof Device){
            buttonsRow.getChildren().add(onOff);
        } else if (object instanceof DeviceGroup) {
            buttonsRow.getChildren().add(paneBuilder(50));
        }
        buttonsRow.setAlignment(Pos.TOP_LEFT);

        newDeviceVBox.getStyleClass().add("deviceRowVBox");
        newDeviceVBox.getChildren().addAll(label, buttonsRow);

        return newDeviceVBox;
    }

    /**
     * Creates a label with the specified text.
     *
     * @param name The text to be displayed in the label.
     * @return A {@link Label} with the specified text.
     */
    private Label createLabel(String name) {
        Label deviceLabel = new Label(name);
        deviceLabel.getStyleClass().add("deviceLabel");
        return deviceLabel;
    }

    /**
     * Creates a {@link Pane} with a specified width, typically used as a spacer.
     *
     * @param width The preferred width of the pane.
     * @return A {@link Pane} with the specified width.
     */
    private Pane paneBuilder(double width) {
        Pane temp = new Pane();
        temp.setPrefWidth(width);
        return temp;
    }
}