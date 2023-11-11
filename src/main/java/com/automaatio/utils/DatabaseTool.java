package com.automaatio.utils;

import com.automaatio.model.database.Weekday;
import com.automaatio.model.database.WeekdayDAO;

public class DatabaseTool {
    private static final WeekdayDAO weekdayDAO = new WeekdayDAO();

    public static void resetWeekdays() {
        if (weekdayDAO.getAll().size() != 7) {
            weekdayDAO.deleteAll();
            weekdayDAO.addObject(new Weekday("Monday", "Maanantai", "Monday_ru", "Monday_ar"));
            weekdayDAO.addObject(new Weekday("Tuesday", "Tiistai", "Tuesday_ru", "Tuesday_ar"));
            weekdayDAO.addObject(new Weekday("Wednesday", "Keskiviikko", "Wednesday_ru", "Wednesday_ar"));
            weekdayDAO.addObject(new Weekday("Thursday", "Torstai", "Thursday_ru", "Thursday_ar"));
            weekdayDAO.addObject(new Weekday("Friday", "Perjantai", "Friday_ru", "Friday_ar"));
            weekdayDAO.addObject(new Weekday("Saturday", "Lauantai", "Saturday_ru", "Saturday_ar"));
            weekdayDAO.addObject(new Weekday("Sunday", "Sunnuntai", "Sunday_ru", "Sunday_ar"));
        }
    }
}
