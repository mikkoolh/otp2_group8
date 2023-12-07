package com.automaatio.controller.mainpage.clickActions;

import javafx.scene.layout.VBox;

/**
 * The ClickActions interface defines the methods that handle click actions in the HomeAutomation application's main page.
 * Implementing classes are expected to provide functionality for expanding and deleting items in the graphical user interface.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public interface ClickActions {
    /**
     * Handles the click action when an expand button is clicked.
     *
     * @param object The object associated with the expand action.
     */
    void onExpandClick(Object object);
    /**
     * Handles the click action when a delete button is clicked.
     *
     * @param object     The object associated with the delete action.
     * @param mainVBox   The main VBox container in the GUI.
     * @param boxToDelete The specific VBox to be deleted from the GUI.
     */
    void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete);
}
