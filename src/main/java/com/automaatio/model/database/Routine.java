package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * The Routine class represents a Routine entity that is stored in the database.
 *
 * @author Matleena Kankaanpää
 * @version 1.0 26.9.2023
 */

@Entity
@Table(name = "routine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private int routineID;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device deviceID;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature featureID;

    @ManyToOne
    @JoinColumn(name = "event_time_id")
    private EventTime eventTime;

    @Column(name = "automated")
    private boolean automated;

    /**
     * Parameterless default constructor
     */
    public Routine() {}

    /**
     * Parameterized constructor
     *
     * @param user          The user associated with the routine
     * @param deviceID      The device associated with the routine
     * @param featureID     The device feature of the associated with the routine
     * @param eventTime     The time the routine takes place
     * @param automated     A boolean indicating whether the routine is automated or not
     */
    public Routine(User user, Device deviceID, Feature featureID, EventTime eventTime, boolean automated) {
        this.user = user;
        this.deviceID = deviceID;
        this.featureID = featureID;
        this.eventTime = eventTime;
        this.automated = automated;
    }

    /**
     * Returns the id of the routine.
     *
     * @return The id of the routine
     */
    public int getRoutineID() {
        return routineID;
    }

    /**
     * Returns the user associated with the routine.
     *
     * @return The user associated with the routine
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the event time associated with the routine.
     *
     * @return The event time associated with the routine
     */
    public EventTime getEventTime() { return eventTime; }

    /**
     * Returns a boolean indicating whether the routine is automated or not.
     *
     * @return True if automation is enabled for the routine, otherwise false
     */
    public boolean getAutomated() {
        return automated;
    }

    /**
     * Sets automation of the routine to enabled or disabled.
     *
     * @param automated A boolean indicating whether automation will be enabled
     *                  or disabled. True if automation will be enabled,
     *                  false if automation will be disabled.
     */
    public void setAutomated(boolean automated) {
        this.automated = automated;
    }

    /**
     * Sets the event time of the routine.
     *
     * @param eventTime An event time to be set for the routine
     */
    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Returns a string containing routine information.
     *
     * @return A string containing the routine id
     */
    @Override
    public String toString() {
        return "Routine=" + routineID;
    }
}
