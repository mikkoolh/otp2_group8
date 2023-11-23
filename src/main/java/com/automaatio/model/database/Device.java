package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * Author: Nikita Nossenko
 * Author: Mikko Hänninen
 * Author: Elmo Erla
 * 
 * This class represents a Device entity that is stored in the database.
 */
@Entity
@Table(name = "device")
public class Device {
    /**
     * The unique identifier for the device.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceID")
    private int deviceID;

    /**
     * Indicates whether the device is currently on or off.
     */
    @Column(name = "onOff")
    private boolean onOff;

    /**
     * Indicates whether the device is set for automation.
     */
    @Column(name = "automation")
    private boolean automation;

    /**
     * The usage data associated with the device.
     */
    @Column(name = "usageData")
    private long usageData;

    /**
     * The name of the device.
     */
    @Column(name = "name")
    private String name;

    /**
     * The model code of the device.
     */
    @Column(name = "modelCode")
    private String modelCode;

    @Column(name = "userName")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "device_group_id")
    private DeviceGroup deviceGroup;

    /**
     * Default constructor for creating a new Device instance.
     */
    public Device() {}

    /**
     * Constructs a new Device instance with the specified parameters.
     *
     * @param usageData   The usage data associated with the device.
     * @param name        The name of the device.
     * @param modelCode   The model code of the device.
     * @param deviceGroup
     */
    public Device(long usageData, String name, String modelCode, DeviceGroup deviceGroup, String userName) {
        onOff = false;
        automation = false;
        this.usageData = usageData;
        this.name = name;
        this.modelCode = modelCode;
        this.deviceGroup = deviceGroup;
        this.userName = userName;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void switchOnOff() {
        onOff = !onOff;
    }

    public String getUserName() { return this.userName; }

    public boolean isAutomation() {
        return automation;
    }

    public void setAutomation(boolean automation) {
        this.automation = automation;
    }

    public long getUsageData() {
        return usageData;
    }

    public void setUsageData(long usageData) {
        this.usageData = usageData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    @Override
    public String toString() {
        return getName();
    }
}

