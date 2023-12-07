package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.utils.BundleLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code DeleteButtonCreator} class implements the {@code IButton} interface
 * to create a delete button. This button, when clicked, performs a deletion action
 * defined in the {@code ClickActions} interface.
 *
 * The class is designed to work with a specific object and two {@link VBox} instances.
 * The first {@code VBox} is the main container, and the second is the specific container
 * that is targeted for deletion.
 */
public class DeleteButtonCreator implements IButton {
    private VBox mainVBox, vBoxToDelete;
    private BundleLoader bundleLoader = new BundleLoader();

    /**
     * Constructs a {@code DeleteButtonCreator} with specified {@link VBox} instances.
     *
     * @param mainVBox The main container {@link VBox}.
     * @param vBoxToDelete The {@link VBox} that is targeted for deletion.
     */
    public DeleteButtonCreator(VBox mainVBox, VBox vBoxToDelete){
        this.mainVBox = mainVBox;
        this.vBoxToDelete = vBoxToDelete;
    }

    /**
     * Creates a delete button with a specific action defined in {@code ClickActions}.
     * The button is associated with an object and two {@link VBox} instances.
     *
     * @param object The object associated with the delete action.
     * @param clickActions The {@code ClickActions} instance defining the delete action.
     * @return A {@link Button} configured with delete functionality.
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


