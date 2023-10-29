package com.automaatio.utils;

import com.automaatio.model.database.Routine;
import com.automaatio.model.database.Weekday;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Matleena Kankaanpää
 * 30.9.2023
 *
 * Utility class for sorting routines
 */

public class RoutineUtils {

    /**
     * Sorts a list of routines by time
     *
     * @param routines A list of routines
     * @return A list of routines sorted by hour and minute
     */
    public List<Routine> sortByTime(List<Routine> routines) {
        List<Routine> list = new ArrayList<>();
        for (Routine routine1 : routines) {
            list.add(routine1);
        }
        list.sort(Comparator.comparing(routine -> routine.getEventTime().getStartTime().toLocalTime()));
        return list;
    }

    /**
     * Checks if all routines in a list are automated
     *
     * @param routines A list of routines
     * @return True if automation is enabled for each routine
     */
    public boolean allAutomated(List<Routine> routines) {
        for (Routine routine : routines) {
            if (!routine.getAutomated()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time formatter
     *
     * @param time A LocalDateTime object
     * @return A string in HH:mm format
     */
    public String getFormattedTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    /**
     * Sorts a list of routines into a HashMap with weekdays as keys
     * @param weekdays
     * @param routines
     * @return
     */
    public LinkedHashMap<String, ArrayList<Routine>> getRoutinesByWeekday(List<Weekday> weekdays, List<Routine> routines) {
        LinkedHashMap<String, ArrayList<Routine>> map = new LinkedHashMap<>();

        // Fill LinkedHashMap keys with weekdays
        for (Weekday w : weekdays) {
            map.put(w.getName(), new ArrayList<Routine>());
        }

        // Add each weekday to the corresponding list in the LinkedHashMap
        for (Routine routine : routines) {
            map.get(routine.getEventTime().getWeekday().getName()).add(routine);
        }
        return map;
    }

    /**
     * Compares the values two LocalTime objects
     * @param startTime Start time
     * @param endTime End time
     * @return True if the end time is after the start time
     */
    public boolean compareTimes(LocalTime startTime, LocalTime endTime) {
        return endTime.isAfter(startTime);
    }
}