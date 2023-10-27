package com.automaatio.model.database;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Device feature entity
 * @author Matleena Kankaanpää
 * 8.9.2023
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
     * @param affectsOthers Boolean indicating whether the feature affects other devices
     * @param isActive Boolean indicating whether the feature is currently in use
     * @param adjustable Boolean indicating whether the feature is adjustable
     * @param description Description of the feature
     * @param timesUsed The amount of times the feature has been used
     */
    public Feature(boolean affectsOthers, boolean isActive, boolean adjustable, String description, int timesUsed) {
        this.affectsOthers = affectsOthers;
        this.isActive = isActive;
        this.adjustable = adjustable;
        this.description = description;
        this.timesUsed = timesUsed;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public boolean getAffectsOthers() {
        return this.affectsOthers;
    }

    public void setAffectsOthers(boolean affectsOthers) {
        this.affectsOthers = affectsOthers;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getAdjustable() {
        return this.adjustable;
    }

    public void setAdjustable(boolean adjustable) {
        this.adjustable = adjustable;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimesUsed() {
        return this.timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }
    @Override
    public String toString() {
        return description;
    }
}