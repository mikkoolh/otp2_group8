package com.automaatio.utils;

import com.automaatio.model.database.Weekday;
import com.automaatio.model.database.WeekdayDAO;

public class DatabaseTool {
    private static WeekdayDAO weekdayDAO = new WeekdayDAO();

    public static void resetWeekdays() {
        if (weekdayDAO.getAll().size() != 7) {
            weekdayDAO.deleteAll();
            weekdayDAO.addObject(new Weekday("Monday"));
            weekdayDAO.addObject(new Weekday("Tuesday"));
            weekdayDAO.addObject(new Weekday("Wednesday"));
            weekdayDAO.addObject(new Weekday("Thursday"));
            weekdayDAO.addObject(new Weekday("Friday"));
            weekdayDAO.addObject(new Weekday("Saturday"));
            weekdayDAO.addObject(new Weekday("Sunday"));
        }
    }
}
