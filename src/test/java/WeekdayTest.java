import com.automaatio.model.database.Weekday;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the Weekday class
 * @author Matleena Kankaanpää
 * 25.11.2023
 */

public class WeekdayTest {
    @Test
    public void testWeekdayConstructorAndGetters() {
        Weekday weekday = new Weekday("Friday", "perjantai", "пятница", "جمعة");
        assertEquals(weekday.getName_en(), "Friday");
        assertEquals(weekday.getName_fi(), "perjantai");
        assertEquals(weekday.getName_ru(), "пятница");
        assertEquals(weekday.getName_ar(), "جمعة");
    }
}