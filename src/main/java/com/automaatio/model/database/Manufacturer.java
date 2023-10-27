package com.automaatio.model.database;

import jakarta.persistence.*;

import java.util.List;

/**
 * Manufacturer entity
 * @author Matleena Kankaanpää
 * 9.9.2023
 */

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    @Column
    private String name;

    @Column
    @OneToMany(mappedBy = "deviceID", cascade = CascadeType.ALL)
    private List<Device> deviceList;

    /**
     * Parameterless constructor
     */
    public Manufacturer() {}

    /**
     * Parameterized constructor
     * @param name Manufacturer name
     */
    public Manufacturer(String name) {
        this.name = name;
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