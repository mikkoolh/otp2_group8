package com.automaatio.model.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The EventTime class represents an EventTime entity that is stored in the database.
 *
 * @author Mikko Hänninen
 * @version 1.0
 * @since 03.09.2023
 */

@Entity
@Table(name="event_time")
public class EventTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_time_id")
    private Long eventTimeId;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "weekday_id")
    private Weekday weekday;

    @OneToMany(mappedBy = "eventTime")
    @Column(name = "history_events")
    private List<HistoryEvents> historyEvents;

    /**
     * Parameterless default constructor
     */
    public EventTime() {}

    /**
     * Parameterized constructor
     *
     * @param startTime     The starting time of the event
     * @param endTime       The ending time of the event
     * @param weekday       The weekday the event takes place on
     */
    public EventTime(LocalDateTime startTime, LocalDateTime endTime, Weekday weekday) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekday = weekday;
    }

    /**
     * Returns the starting time of the event
     * @return A LocalDateTime object containing the starting time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the ending time of the event
     * @return A LocalDateTime object containing the ending time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the weekday the event takes place on
     * @return The weekday of the event
     */
    public Weekday getWeekday() {
        return weekday;
    }

    /**
     * Returns the weekday and starting time information of the event as a String
     * @return A string describing the weekday and starting time of the event
     */
    @Override
    public String toString() {
        return weekday.toString() +", " + startTime;
    }
}