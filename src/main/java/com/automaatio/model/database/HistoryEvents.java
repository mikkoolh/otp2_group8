package com.automaatio.model.database;

import jakarta.persistence.*;

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

    public User getUser() {
        return username;
    }

    public void setUser(User user) {
        username = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "HistoryEvent=" + eventId;
    }
}