package com.automaatio.utils;

import com.automaatio.model.database.Weekday;
import java.util.List;
import java.util.Locale;

/**
 * Functions for localization.
 */

public class LocalizationTool {
    public String localizeWeekday(Weekday weekday) {
        Locale currentLocale = (new CurrentLocale()).getCurrentLocale();

        switch (currentLocale.toString()) {
            case "fi_FI":
                return weekday.getName_fi();
            case "ru_RU":
                return weekday.getName_ru();
            case "ar_SA":
                return weekday.getName_ar();
            case "en_US":
            default:
                return weekday.getName_en();
        }
    }

    /**
     * Rearranges a list of weekdays according to the currently selected language
     * @param initialList List of weekdays in a default order
     * @return Mon-Sun if the current language is Finnish/Russian, Sun-Sat if English/Arabic
     */
    public List<Weekday> sortWeekdays(List<Weekday> initialList) {
        Locale currentLocale = (new CurrentLocale()).getCurrentLocale();

        if (currentLocale.toString().equals("ar_SA") || currentLocale.toString().equals("en_US")) {
            List<Weekday> sortedList = new java.util.ArrayList<>(List.copyOf(initialList));
            System.out.println("ar/en");
            Weekday sunday = sortedList.remove(6);
            sortedList.add(0, sunday);
            System.out.println(sortedList);
            return sortedList;
        }

        System.out.println("fi/ru");
        return initialList;
    }
}
