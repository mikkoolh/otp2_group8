package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * The Feature class represents a Feature entity that is stored in the database.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 * @since 8.9.2023
 */

@Entity
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    private int featureId;

    @Column(name = "affects_others")
    private boolean affectsOthers;

    // on/off
    @Column(name = "is_active")
    private boolean isActive;

    @Column
    private boolean adjustable;

    @Column
    private String description;

    @Column(name = "times_used")
    private int timesUsed;

    /**
     * Parameterless constructor
     */
    public Feature() {}

    /**
     * Parameterized constructor
     * @param affectsOthers     Boolean indicating whether the feature affects other devices
     * @param isActive          Boolean indicating whether the feature is currently in use
     * @param adjustable        Boolean indicating whether the feature is adjustable
     * @param description       Description of the feature
     * @param timesUsed         The amount of times the feature has been used
     */
    public Feature(boolean affectsOthers, boolean isActive, boolean adjustable, String description, int timesUsed) {
        this.affectsOthers = affectsOthers;
        this.isActive = isActive;
        this.adjustable = adjustable;
        this.description = description;
        this.timesUsed = timesUsed;
    }

    /**
     * Returns the description of the feature
     * @return The description of the feature
     */
    @Override
    public String toString() {
        return description;
    }
}