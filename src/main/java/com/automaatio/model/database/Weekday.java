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
    private String name_en;

    @Column
    private String name_fi;

    @Column
    private String name_ru;

    @Column
    private String name_ar;

    public Weekday() {}

    /**
     * Parametrized constructor
     */
    public Weekday(String name_en, String name_fi, String name_ru, String name_ar) {
        this.name_en = name_en;
        this.name_fi = name_fi;
        this.name_ru = name_ru;
        this.name_ar = name_ar;
    }

    public int getWeekdayId() {
        return weekdayId;
    }

    public String getName_en() {
        return name_en;
    }

    public String getName_fi() {
        return name_fi;
    }

    public String getName_ru() {
        return name_ru;
    }

    public String getName_ar() {
        return name_ar;
    }
}