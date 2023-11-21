import com.automaatio.model.database.Weekday;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.LocalizationTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationToolTest {

    @Mock
    private CacheSingleton cacheSingleton;

    private LocalizationTool localizationTool;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cacheSingleton = CacheSingleton.getInstance();
        localizationTool = new LocalizationTool();
    }

    @Test
    void testLocalizeWeekdayInEnglish() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("en", "US"));
        assertEquals("Sunday", localizationTool.localizeWeekday(weekday));
    }

    @Test
    void testLocalizeWeekdayInFinnish() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("fi", "FI"));
        assertEquals("Sunnuntai", localizationTool.localizeWeekday(weekday));
    }

    @Test
    void testLocalizeWeekdayInRussian() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("ru", "RU"));
        assertEquals("Воскресенье", localizationTool.localizeWeekday(weekday));
    }

    @Test
    void testLocalizeWeekdayInArabic() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("ar", "SA"));
        assertEquals("الأحد", localizationTool.localizeWeekday(weekday));
    }

    @Test
    void testSortWeekdaysForEnglishLocale() {
        List<Weekday> weekdays = Arrays.asList(
                new Weekday("Monday", "Maanantai", "Понедельник", "الاثنين"),
                new Weekday("Tuesday", "Tiistai", "Вторник", "الثلاثاء"),
                new Weekday("Wednesday", "Keskiviikko", "Среда", "الأربعاء"),
                new Weekday("Thursday", "Torstai", "Четверг", "الخميس"),
                new Weekday("Friday", "Perjantai", "Пятница", "الجمعة"),
                new Weekday("Saturday", "Lauantai", "Суббота", "السبت"),
                new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد")
        );
        cacheSingleton.setTempLocale(new Locale("en", "US"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("Sunday", sortedWeekdays.get(0).getName_en());
    }

    @Test
    void testSortWeekdaysForFinnishLocale() {
        List<Weekday> weekdays = Arrays.asList(
                new Weekday("Monday", "Maanantai", "Понедельник", "الاثنين"),
                new Weekday("Tuesday", "Tiistai", "Вторник", "الثلاثاء"),
                new Weekday("Wednesday", "Keskiviikko", "Среда", "الأربعاء"),
                new Weekday("Thursday", "Torstai", "Четверг", "الخميس"),
                new Weekday("Friday", "Perjantai", "Пятница", "الجمعة"),
                new Weekday("Saturday", "Lauantai", "Суббота", "السبت"),
                new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد")
        );
        cacheSingleton.setTempLocale(new Locale("fi", "FI"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("Maanantai", sortedWeekdays.get(0).getName_fi());
    }

    @Test
    void testSortWeekdaysForRussianLocale() {
        List<Weekday> weekdays = Arrays.asList(
                new Weekday("Monday", "Maanantai", "Понедельник", "الاثنين"),
                new Weekday("Tuesday", "Tiistai", "Вторник", "الثلاثاء"),
                new Weekday("Wednesday", "Keskiviikko", "Среда", "الأربعاء"),
                new Weekday("Thursday", "Torstai", "Четверг", "الخميس"),
                new Weekday("Friday", "Perjantai", "Пятница", "الجمعة"),
                new Weekday("Saturday", "Lauantai", "Суббота", "السبت"),
                new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد")
        );
        cacheSingleton.setTempLocale(new Locale("ru", "RU"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("Понедельник", sortedWeekdays.get(0).getName_ru());
    }

    @Test
    void testSortWeekdaysForArabicLocale() {
        List<Weekday> weekdays = Arrays.asList(
                new Weekday("Monday", "Maanantai", "Понедельник", "الاثنين"),
                new Weekday("Tuesday", "Tiistai", "Вторник", "الثلاثاء"),
                new Weekday("Wednesday", "Keskiviikko", "Среда", "الأربعاء"),
                new Weekday("Thursday", "Torstai", "Четверг", "الخميس"),
                new Weekday("Friday", "Perjantai", "Пятница", "الجمعة"),
                new Weekday("Saturday", "Lauantai", "Суббота", "السبت"),
                new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد")
        );
        cacheSingleton.setTempLocale(new Locale("ar", "SA"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("الأحد", sortedWeekdays.get(0).getName_ar());
    }
}
