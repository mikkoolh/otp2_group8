package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DeleteButtonCreator implements IButton {

    private String deleteTxt = "Delete";
    private VBox mainVBox, vBoxToDelete;
    public DeleteButtonCreator(VBox mainVBox, VBox vBoxToDelete){
        this.mainVBox = mainVBox;
        this.vBoxToDelete = vBoxToDelete;
    }
    @Override
    public Button create(Object object, ClickActions clickActions) {
        Button delete = new Button(deleteTxt);
        Object objectToDelete = object;
        delete.getStyleClass().add("deleteBtn");
        delete.setOnAction(event -> clickActions.onDeleteClick(objectToDelete, mainVBox, vBoxToDelete));
        return delete;
    }


}


