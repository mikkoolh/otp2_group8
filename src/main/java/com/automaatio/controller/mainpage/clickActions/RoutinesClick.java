package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The RoutinesClick class implements the ClickActions interface and defines click actions specific to routines in the HomeAutomation application's main page.
 * It handles expand actions for routines, updating the graphical user interface accordingly.
 * This class does not provide functionality for delete actions, and the onDeleteClick method is left empty.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class RoutinesClick implements ClickActions {
    private CacheSingleton cache = CacheSingleton.getInstance();
    private BundleLoader bundleLoader = new BundleLoader();

    /**
     * Handles the click action when an expand button for a routine is clicked.
     * Sets the selected routine in the cache and loads the corresponding routine view in the main pane.
     *
     * @param object The Device object associated with the expand action.
     */
    @Override
    public void onExpandClick(Object object) {
        cache.setDevice((Device) object);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/routine.fxml"));
            loader.setResources(bundleLoader.loadResourceByUsersLocale());
            Parent newView = loader.load();
            cache.getMainPane().getChildren().clear();
            cache.getMainPane().getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Does nothing for the delete action since routines cannot be deleted in this context.
     *
     * @param object      The object associated with the delete action (not used for routines).
     * @param mainVBox    The main VBox container in the user interface.
     * @param boxToDelete The specific VBox representing the routine (not used for routines).
     */
    @Override
    public void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete) {

    }
}
