package com.automaatio.utils;

import com.automaatio.model.database.Weekday;
import java.util.List;
import java.util.Locale;

/**
 * The LocalizationTool class provides utility methods for localization processes.
 *
 * @auhor Matleena Kankaanpää
 * @version 1.0
 */

public class LocalizationTool {

    /**
     * Receives a Weekday object as argument. Translations of the weekday name are
     * stored in the Weekday object's fields. The method uses an instance of CurrentLocale
     * to check which locale is currently selected in the application, and returns the name
     * of the weekday in the corresponding language.
     *
     * @param weekday A Weekday object
     * @return The name of the weekday in the current locale
     */
    public String localizeWeekday(Weekday weekday) {
        Locale currentLocale = (new CurrentLocale()).getCurrentLocale();

        if (weekday == null)
            System.out.println("weekday null");

        switch (currentLocale.toString()) {
            case "fi_FI":
                return weekday.getName_fi();
            case "ru_RU":
                return weekday.getName_ru();
            case "ar_SA":
                return weekday.getName_ar();
            case "en_US":
                return weekday.getName_en();
            default:
                return weekday.getName_en();
        }
    }

    /**
     * Rearranges a list of weekdays according to the currently selected language.
     * The method uses an instance of CurrentLocale to check the current locale
     * of the application.
     *
     * @param initialList List of weekdays in the default order
     * @return Mon-Sun if the current language is Finnish/Russian, Sun-Sat if English/Arabic
     */
    public List<Weekday> sortWeekdays(List<Weekday> initialList) {
        Locale currentLocale = (new CurrentLocale()).getCurrentLocale();

        if (currentLocale.toString().equals("ar_SA") || currentLocale.toString().equals("en_US")) {
            List<Weekday> sortedList = new java.util.ArrayList<>(List.copyOf(initialList));
            Weekday sunday = sortedList.remove(6);
            sortedList.add(0, sunday);
            System.out.println(sortedList);
            return sortedList;
        }
        return initialList;
    }
}
