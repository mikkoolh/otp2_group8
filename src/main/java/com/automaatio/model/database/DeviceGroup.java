package com.automaatio.model.database;

import jakarta.persistence.*;

import java.util.List;

/**
 * Device group entity
 * @author Matleena Kankaanpää 10.9.2023
 * @author Mikko Hänninen 20.9.2023
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
     * Parameterless constructor
     */
    public DeviceGroup() {}

    /**
     * Parameterized constructor
     * @param name Device group name
     */
    public DeviceGroup(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public int getDeviceGroupId() {
        return this.deviceGroupId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
