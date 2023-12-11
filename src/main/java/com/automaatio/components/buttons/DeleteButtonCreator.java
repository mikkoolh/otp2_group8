package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.utils.BundleLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The DeleteButtonCreator class extends the IButton interface
 * to create a 'Delete' button.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */

public class DeleteButtonCreator implements IButton {
    private VBox mainVBox, vBoxToDelete;
    private BundleLoader bundleLoader = new BundleLoader();

    /**
     * Class constructor
     *
     * @param mainVBox      The main container
     * @param vBoxToDelete  The container targeted for deletion
     */
    public DeleteButtonCreator(VBox mainVBox, VBox vBoxToDelete){
        this.mainVBox = mainVBox;
        this.vBoxToDelete = vBoxToDelete;
    }

    /**
     * Creates a delete button with an associated click action passed as parameter
     *
     * @param object        The object associated with the delete action
     * @param clickActions  The action to be executed when the button is clicked
     * @return              A button with the text 'Delete' translated to the current locale
     */
    @Override
    public Button create(Object object, ClickActions clickActions) {
        Button delete = new Button(bundleLoader.loadResourceByUsersLocale().getString("deleteBtnTxt"));
        Object objectToDelete = object;
        delete.getStyleClass().add("deleteBtn");
        delete.setOnAction(event -> clickActions.onDeleteClick(objectToDelete, mainVBox, vBoxToDelete));
        return delete;
    }
}


