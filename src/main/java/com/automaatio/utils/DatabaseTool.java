package com.automaatio.utils;

import com.automaatio.model.database.Weekday;
import com.automaatio.model.database.WeekdayDAO;

public class DatabaseTool {
    private static final WeekdayDAO weekdayDAO = new WeekdayDAO();

    public static void resetWeekdays() {
        if (weekdayDAO.getAll().size() != 7) {
            weekdayDAO.deleteAll();
            weekdayDAO.addObject(new Weekday("Monday", "Maanantai", "понедельник", "الأثنين"));
            weekdayDAO.addObject(new Weekday("Tuesday", "Tiistai", "вторник", "الثلاثاء"));
            weekdayDAO.addObject(new Weekday("Wednesday", "Keskiviikko", "среда", "الأربعاء"));
            weekdayDAO.addObject(new Weekday("Thursday", "Torstai", "четверг", "الخميس"));
            weekdayDAO.addObject(new Weekday("Friday", "Perjantai", "пятниц", "الجمعه"));
            weekdayDAO.addObject(new Weekday("Saturday", "Lauantai", "суббота", "السبت"));
            weekdayDAO.addObject(new Weekday("Sunday", "Sunnuntai", "воскресенье", "الأحد"));
        }
    }
}
