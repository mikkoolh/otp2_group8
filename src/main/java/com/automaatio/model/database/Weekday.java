package com.automaatio.model.database;
import jakarta.persistence.*;

/**
 * @author Mikko Hänninen
 * @author Elmo Erla
 * 11.09.2023
 *
 * Weekday-table for EventTime
 */

@Entity
@Table(name = "weekday")
public class Weekday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekday_id")
    private int weekdayId;

    @Column
    private String name;

    public Weekday() {}

    /**
     * Parametrized constructor
     * @param name name of the day
     */
    public Weekday(String name) {
        this.name = name;
    }

    public int getWeekdayId() {
        return weekdayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}