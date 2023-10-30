package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.utils.BundleLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteButtonCreator implements IButton {


    private VBox mainVBox, vBoxToDelete;
    private BundleLoader bundleLoader = new BundleLoader();
    public DeleteButtonCreator(VBox mainVBox, VBox vBoxToDelete){
        this.mainVBox = mainVBox;
        this.vBoxToDelete = vBoxToDelete;
    }
    @Override
    public Button create(Object object, ClickActions clickActions) {
        Button delete = new Button(bundleLoader.loadResourceByUsersLocale().getString("deleteBtnTxt"));
        Object objectToDelete = object;
        delete.getStyleClass().add("deleteBtn");
        delete.setOnAction(event -> clickActions.onDeleteClick(objectToDelete, mainVBox, vBoxToDelete));
        return delete;
    }


}


