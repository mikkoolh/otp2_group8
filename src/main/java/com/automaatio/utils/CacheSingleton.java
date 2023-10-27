package com.automaatio.utils;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.User;
import javafx.scene.layout.Pane;

public class CacheSingleton {

    private static CacheSingleton instance;
    private DeviceGroup room;
    private User user;
    private Device device;

    private Pane mainPane, menuPane;


    private CacheSingleton() {
    }

    public static CacheSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSingleton();
        }
        return instance;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setRoom(DeviceGroup room) {
        System.out.println("room set in singleton: " +room.getName());
        this.room = room;
    }

    public void setDevice(Device device) {
        System.out.println("device set in singleton: " + device.getName());
        this.device = device;
    }

    public User getUser(){
        return user;
    }

    public DeviceGroup getRoom(){ return room; }

    public Device getDevice() {
        System.out.println("getDevice in singleton: " + device.getName());
        return device;
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public Pane getMenuPane() {
        return menuPane;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public void setMenuPane(Pane menuPane) {
        this.menuPane = menuPane;
    }
}
