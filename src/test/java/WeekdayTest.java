import com.automaatio.model.database.Weekday;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Weekday class
 *
 * @author Matleena Kankaanpää
 * @version 1.0 25.11.2023
 */

public class WeekdayTest {

    /**
     * Tests the constructor, getters and setters
     */
    @Test
    public void testWeekdayConstructorAndGetters() {
        Weekday weekday = new Weekday("Friday", "perjantai", "пятница", "جمعة");
        assertEquals(weekday.getName_en(), "Friday");
        assertEquals(weekday.getName_fi(), "perjantai");
        assertEquals(weekday.getName_ru(), "пятница");
        assertEquals(weekday.getName_ar(), "جمعة");
    }
}