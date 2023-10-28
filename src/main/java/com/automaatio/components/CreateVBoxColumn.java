package com.automaatio.components;

import com.automaatio.components.buttons.ExpandButtonCreator;
import com.automaatio.components.buttons.ToggleButtonCreator;
import com.automaatio.components.buttons.DeleteButtonCreator;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;


public class CreateVBoxColumn {
    private Button deleteBtn, expandBtn;
    private ToggleButton onOff;
    private Label label;
    private final int vBoxSpacing = 10, hBoxSpacing = 20;

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

    private Label createLabel(String name) {
        Label deviceLabel = new Label(name);
        deviceLabel.getStyleClass().add("deviceLabel");
        return deviceLabel;
    }
    private Pane paneBuilder(double width){
        Pane temp = new Pane();
        temp.setPrefWidth(width);
        return temp;
    }
}