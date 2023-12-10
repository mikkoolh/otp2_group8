package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * The Device class represents a Device entity that is stored in the database.
 *
 * @author Nikita Nossenko, Mikko Hänninen, Elmo Erla
 * @version 1.0
 */

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceID")
    private int deviceID;

    @Column(name = "onOff")
    private boolean onOff;

    @Column(name = "automation")
    private boolean automation;

    @Column(name = "usageData")
    private long usageData;

    @Column(name = "name")
    private String name;

    @Column(name = "modelCode")
    private String modelCode;

    @Column(name = "userName")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "device_group_id")
    private DeviceGroup deviceGroup;

    /**
     * Parameterless default constructor
     */
    public Device() {}

    /**
     * Parameterized constructor
     *
     * @param usageData   The usage data associated with the device.
     * @param name        The name of the device.
     * @param modelCode   The model code of the device.
     * @param deviceGroup The device group that the device belongs to.
     * @param userName    The username of the user associated with the device.
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

    /**
     * Returns the device ID.
     *
     * @return The device ID
     */
    public int getDeviceID() {
        return deviceID;
    }

    /**
     * Returns a boolean indicating whether the device is currently on or off.
     *
     * @return True if the device is currently on, otherwise false
     */
    public boolean isOnOff() {
        return onOff;
    }

    /**
     * Switches the device on if it was previously off. If the device was
     * on, it is switched off.
     */
    public void switchOnOff() {
        onOff = !onOff;
    }

    /**
     * Returns the username of the user associated with the device.
     *
     * @return The username of the user associated with the device
     */
    public String getUserName() { return this.userName; }

    /**
     * Returns a boolean indicating whether automation is enabled for the device or not.
     *
     * @return True if automation is enabled for the device, otherwise false
     */
    public boolean isAutomation() {
        return automation;
    }

    /**
     * Enables or disables automation of the device.
     *
     * @param automation A boolean incidating whether automation will be enabled or disabled.
     *                   If true is passed, automation will be enabled. If false is passed,
     *                   automation will be disabled.
     */
    public void setAutomation(boolean automation) {
        this.automation = automation;
    }

    /**
     * Returns the usage data of the device.
     *
     * @return The usage data of the device
     */
    public long getUsageData() {
        return usageData;
    }

    /**
     * Sets the usage data of the device.
     *
     * @param usageData The usage data to be set for the device
     */
    public void setUsageData(long usageData) {
        this.usageData = usageData;
    }

    /**
     * Returns the name of the device.
     *
     * @return The name of the device
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the device.
     *
     * @param name The name to be given to the device
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the model code of the device.
     *
     * @return The model code of the device
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * Sets the model code of the device.
     *
     * @param modelCode The model code of the device
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * Sets the device group of the device.
     *
     * @param deviceGroup The device group that the device will be added to
     */
    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    /**
     * Returns the device group of the device.
     *
     * @return The device group of the device
     */
    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    /**
     * Returns the name of the device.
     *
     * @return The name of the device
     */
    @Override
    public String toString() {
        return getName();
    }
}

