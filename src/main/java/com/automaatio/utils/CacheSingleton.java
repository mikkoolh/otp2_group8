package com.automaatio.utils;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.User;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.layout.Pane;
import java.util.Locale;

/**
 * The CacheSingleton class creates a singleton object which functions as a cache
 * to store user information, language and UI orientation settings.
 *
 * @author Mikko Hänninen, Nikita Nossenko, Elmo Erla, Matleena Kankaanpää
 * @version 1.0
 */

public class CacheSingleton {
    private static CacheSingleton instance;
    private DeviceGroup room;
    private User user;
    private Device device;
    private Locale tempLocale = new Locale("en", "US");
    private FXMLLoader currentLoader;
    private Pane mainPane, menuPane;
    private NodeOrientation nodeOrientation;

    private CacheSingleton() {
    }

    /**
     * Returns the single instance of the cache object.
     * The object is created if it doesn't exist yet.
     *
     * @return A singleton cache object
     */
    public static CacheSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSingleton();
        }
        return instance;
    }

    /**
     * Saves a user in the cache when logging in
     *
     * @param user The user currently logged in
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Saves a room in the cache when clicked on by the user for viewing or editing
     *
     * @param room The room currently selected by the user
     */
    public void setRoom(DeviceGroup room) {
        System.out.println("room set in singleton: " +room.getName());
        this.room = room;
    }

    /**
     * Saves a device in the cache when clicked on by the user for viewing or editing
     *
     * @param device The device currently selected by the user
     */
    public void setDevice(Device device) {
        System.out.println("device set in singleton: " + device.getName());
        this.device = device;
    }

    /**
     * Returns the user currently saved in the cache
     *
     * @return The user currently logged in
     */
    public User getUser(){
        return user;
    }

    /**
     * Returns the room currently saved in the cache
     *
     * @return The room currently being viewed or edited
     */
    public DeviceGroup getRoom(){ return room; }

    /**
     * Returns the device currently saved in the cache
     *
     * @return The device currently being viewed or edited
     */
    public Device getDevice() {
        System.out.println("getDevice in singleton: " + device.getName());
        return device;
    }

    /**
     * Sets the node orientation (RTL/LTR) to be applied in the layout
     *
     * @param nodeOrientation A NodeOrientation object which determines the layout orientation to be applied
     */
    public void setDirection(NodeOrientation nodeOrientation) {
        this.nodeOrientation = nodeOrientation;
    }

    /**
     * Returns the currently applied node orientation (RTL/LTR)
     *
     * @return A NodeOrientation object containing the current layout orientation
     */
    public NodeOrientation getDirection(){
        return nodeOrientation;
    }

    /**
     * Retrieves the main pane element of the UI. Used when the main pane
     * needs to be targeted to display different contents.
     *
     * @return The main pane element of the UI
     */
    public Pane getMainPane() {
        return mainPane;
    }

    /**
     * Sets a temporary locale in the cache. Used to save locale information
     * when the language is changed from the login screen when a user isn't logged in.
     *
     * @param tempLocale A temporary locale
     */
    public void setTempLocale(Locale tempLocale) {
        this.tempLocale = tempLocale;
    }

    /**
     * Returns the current temporary locale which is not associated with
     * a user. Used to retrieve the current locale when a user isn't logged in.
     *
     * @return tempLocale The current locale when a user isn't logged in
     */
    public Locale getTempLocale() {
        return tempLocale;
    }

    /**
     * Sets a pane element as the main pane in the cache.
     *
     * @param mainPane A pane element
     */
    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    /**
     * Sets a pane element as the menu pane in the cache.
     *
     * @param menuPane A pane element
     */
    public void setMenuPane(Pane menuPane) {
        this.menuPane = menuPane;
    }

    /**
     * Returns the FXMLLoader currently stored in the cache
     *
     * @return The FXMLLoader object currently stored in the cache
     */
    public FXMLLoader getCurrentLoader() {
        return currentLoader;
    }

    /**
     * Sets an FXMLLoader object as the current FXMLLoader in the cache
     *
     * @param currentLoader An FXMLLoader to be stored in the cache as the current loader
     */
    public void setCurrentLoader(FXMLLoader currentLoader) {
        this.currentLoader = currentLoader;
    }
}
