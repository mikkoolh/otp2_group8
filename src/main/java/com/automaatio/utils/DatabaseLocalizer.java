package com.automaatio.utils;

import com.automaatio.model.database.Weekday;
import java.util.Locale;

public class DatabaseLocalizer {
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
}
