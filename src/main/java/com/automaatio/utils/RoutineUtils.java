package com.automaatio.utils;

import com.automaatio.model.database.Routine;
import com.automaatio.model.database.Weekday;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.text.DateFormat;
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
     * @param time A LocalDateTime object
     * @return The time as a String in the current locale (HH:mm/hh:mm)
     */
    public String getFormattedTime(LocalDateTime time) {
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.SHORT, (new CurrentLocale().getCurrentLocale()));
        Date tempDate = new Date();
        tempDate.setHours(time.toLocalTime().getHour());
        tempDate.setMinutes(time.toLocalTime().getMinute());
        return timeFormatter.format(tempDate);
    }

    /**
     * Sorts a list of routines into a HashMap with weekdays as keys
     * @param weekdays
     * @param routines
     * @return
     */
    public LinkedHashMap<String, ArrayList<Routine>> getRoutinesByWeekday(List<Weekday> weekdays, List<Routine> routines) {
        LinkedHashMap<String, ArrayList<Routine>> map = new LinkedHashMap<>();
        LocalizationTool localizer = new LocalizationTool();

        // Fill LinkedHashMap keys with localized weekday names
        for (Weekday w : weekdays) {
            String localizedName = localizer.localizeWeekday(w);
            map.put(localizedName, new ArrayList<>());
        }

        // Add each weekday to the corresponding list in the LinkedHashMap
        for (Routine routine : routines) {
            String localizedName = localizer.localizeWeekday(routine.getEventTime().getWeekday());
            map.get(localizedName).add(routine);
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