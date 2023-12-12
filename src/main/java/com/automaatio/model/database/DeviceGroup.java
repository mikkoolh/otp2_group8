package com.automaatio.model.database;

import jakarta.persistence.*;

import java.util.List;

/**
 * The DeviceGroup class represents a DeviceGroup entity that is stored in the database.
 *
 * @author Mikko Hänninen, Matleena Kankaanpää
 * @version 1.0
 * @since 20.9.2023
 */

@Entity
@Table(name = "device_group")
public class DeviceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_group_id")
    private int deviceGroupId;

    @Column
    private String name;

    @OneToMany(mappedBy = "deviceGroup", cascade = CascadeType.ALL) // Assuming Device entity has a reference to DeviceGroup named "deviceGroup"
    private List<Device> deviceList;

    // User reference with the userName field linking to User's username
    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private User user;

    /**
     * Parameterless default constructor
     */
    public DeviceGroup() {}

    /**
     * Parameterized constructor
     *
     * @param name        The name of the device group.
     * @param user        The user associated with the device group.
     */
    public DeviceGroup(String name, User user) {
        this.name = name;
        this.user = user;
    }

    /**
     * Returns the device group ID
     * @return The device group ID
     */
    public int getDeviceGroupId() {
        return this.deviceGroupId;
    }

    /**
     * Returns the name of the device group
     * @return The name of the device group
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new name for the device group
     * @param name The name to be given to the device group
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user associated with the device group
     * @return The user associated with the device group
     */
    public User getUser() { return user; }

    /**
     * Returns the name of the device group
     * @return The name of the device group
     */
    @Override
    public String toString() {
        return getName();
    }
}
