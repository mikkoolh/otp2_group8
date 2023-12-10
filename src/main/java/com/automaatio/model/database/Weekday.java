package com.automaatio.model.database;
import jakarta.persistence.*;

/**
 * The Weekday class represents a Weekday entity that is stored in the database.
 *
 * @author Mikko Hänninen, Elmo Erla
 * @version 1.0 11.09.2023
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

    /**
     * Parameterless default constructor
     */
    public Weekday() {}

    /**
     * Parameterized constructor
     *
     * @param name_en   The English name of the weekday
     * @param name_fi   The Finnish name of the weekday
     * @param name_ru   The Russian name of the weekday
     * @param name_ar   The Arabic name of the weekday
     */
    public Weekday(String name_en, String name_fi, String name_ru, String name_ar) {
        this.name_en = name_en;
        this.name_fi = name_fi;
        this.name_ru = name_ru;
        this.name_ar = name_ar;
    }

    /**
     * Returns the id of the weekday
     * @return The id of the weekday
     */
    public int getWeekdayId() {
        return weekdayId;
    }

    /**
     * Returns the English name of the weekday
     * @return The English name of the weekday
     */
    public String getName_en() {
        return name_en;
    }

    /**
     * Returns the Finnish name of the weekday
     * @return The Finnish name of the weekday
     */
    public String getName_fi() {
        return name_fi;
    }

    /**
     * Returns the Russian name of the weekday
     * @return The Russian name of the weekday
     */
    public String getName_ru() {
        return name_ru;
    }

    /**
     * Returns the Arabic name of the weekday
     * @return The Arabic name of the weekday
     */
    public String getName_ar() {
        return name_ar;
    }
}