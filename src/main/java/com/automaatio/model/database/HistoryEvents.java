package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * The HistoryEvents class represents a HistoryEvents entity that is stored in the database.
 *
 * @author Nikita Nossenko, Mikko Hänninen, Elmo Erla, Matleena Kankaanpää
 * @version 1.0
 */

@Entity
@Table(name = "history_events")
public class HistoryEvents {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "event_time_id")
    private EventTime eventTime;

    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;

    /**
     * Parameterless default constructor
     */
    public HistoryEvents() {}

    /**
     * Parameterized constructor
     *
     * @param eventTime The time the event takes place
     * @param username  The user associated with the event
     * @param device    The device associated with the event
     * @param feature   The feature associated with the event
     */
    public HistoryEvents(EventTime eventTime, User username, Device device, Feature feature) {
        this.eventTime = eventTime;
        this.username = username;
        this.device = device;
        this.feature = feature;
    }

    /**
     * Returns the user associated with the event
     * @return The user associated with the event
     */
    public User getUser() {
        return username;
    }

    /**
     * Sets a user for the event
     * @param user A user to be associated with the event
     */
    public void setUser(User user) {
        username = user;
    }

    /**
     * Returns the device associated with the event
     * @return The device associated with the event
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets a device for the event
     * @param device A device to be associated with the event
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * Returns a string containing event information
     * @return A string containing the event id
     */
    @Override
    public String toString() {
        return "HistoryEvent=" + eventId;
    }
}