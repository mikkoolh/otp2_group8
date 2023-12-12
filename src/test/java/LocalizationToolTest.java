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

/**
 * The {@code LocalizationToolTest} class contains unit tests for the {@link LocalizationTool} class.
 * It evaluates the functionality of localizing weekdays and sorting them based on different locales.
 *
 * This test class ensures that the {@code LocalizationTool} correctly localizes weekday names and sorts
 * them according to the order specific to each locale. The tests cover various locales including English,
 * Finnish, Russian, and Arabic.
 *
 * @author Elmo Erla
 * @version 1.0
 */
class LocalizationToolTest {
    @Mock
    private CacheSingleton cacheSingleton;
    private LocalizationTool localizationTool;

    /**
     * Sets up the test environment before each test. This includes initializing mocks
     * and setting up the {@link LocalizationTool} instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cacheSingleton = CacheSingleton.getInstance();
        localizationTool = new LocalizationTool();
    }

    /**
     * Tests the localization of a weekday name in English.
     * It verifies that the weekday name is correctly localized to English.
     */
    @Test
    void testLocalizeWeekdayInEnglish() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("en", "US"));
        assertEquals("Sunday", localizationTool.localizeWeekday(weekday));
    }

    /**
     * Tests the localization of a weekday name in Finnish.
     * It verifies that the weekday name is correctly localized to Finnish.
     */
    @Test
    void testLocalizeWeekdayInFinnish() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("fi", "FI"));
        assertEquals("Sunnuntai", localizationTool.localizeWeekday(weekday));
    }

    /**
     * Tests the localization of a weekday name in Russian.
     * It verifies that the weekday name is correctly localized to Russian.
     */
    @Test
    void testLocalizeWeekdayInRussian() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("ru", "RU"));
        assertEquals("Воскресенье", localizationTool.localizeWeekday(weekday));
    }

    /**
     * Tests the localization of a weekday name in Arabic.
     * It verifies that the weekday name is correctly localized to Arabic.
     */
    @Test
    void testLocalizeWeekdayInArabic() {
        Weekday weekday = new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد");
        cacheSingleton.setTempLocale(new Locale("ar", "SA"));
        assertEquals("الأحد", localizationTool.localizeWeekday(weekday));
    }

    /**
     * Tests the sorting of weekdays for the English locale.
     * It verifies that the weekdays are sorted correctly for the English locale.
     */
    @Test
    void testSortWeekdaysForEnglishLocale() {
        List<Weekday> weekdays = getWeekdays();
        cacheSingleton.setTempLocale(new Locale("en", "US"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("Sunday", sortedWeekdays.get(0).getName_en());
    }

    /**
     * Tests the sorting of weekdays for the Finnish locale.
     * It verifies that the weekdays are sorted correctly for the Finnish locale.
     */
    @Test
    void testSortWeekdaysForFinnishLocale() {
        List<Weekday> weekdays = getWeekdays();
        cacheSingleton.setTempLocale(new Locale("fi", "FI"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("Maanantai", sortedWeekdays.get(0).getName_fi());
    }

    /**
     * Tests the sorting of weekdays for the Russian locale.
     * It verifies that the weekdays are sorted correctly for the Russian locale.
     */
    @Test
    void testSortWeekdaysForRussianLocale() {
        List<Weekday> weekdays = getWeekdays();
        cacheSingleton.setTempLocale(new Locale("ru", "RU"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("Понедельник", sortedWeekdays.get(0).getName_ru());
    }

    /**
     * Tests the sorting of weekdays for the Arabic locale.
     * It verifies that the weekdays are sorted correctly for the Arabic locale.
     */
    @Test
    void testSortWeekdaysForArabicLocale() {
        List<Weekday> weekdays = getWeekdays();
        cacheSingleton.setTempLocale(new Locale("ar", "SA"));
        List<Weekday> sortedWeekdays = localizationTool.sortWeekdays(weekdays);
        assertEquals("الأحد", sortedWeekdays.get(0).getName_ar());
    }

    private static List<Weekday> getWeekdays() {
        List<Weekday> weekdays = Arrays.asList(
                new Weekday("Monday", "Maanantai", "Понедельник", "الاثنين"),
                new Weekday("Tuesday", "Tiistai", "Вторник", "الثلاثاء"),
                new Weekday("Wednesday", "Keskiviikko", "Среда", "الأربعاء"),
                new Weekday("Thursday", "Torstai", "Четверг", "الخميس"),
                new Weekday("Friday", "Perjantai", "Пятница", "الجمعة"),
                new Weekday("Saturday", "Lauantai", "Суббота", "السبت"),
                new Weekday("Sunday", "Sunnuntai", "Воскресенье", "الأحد")
        );
        return weekdays;
    }
}
