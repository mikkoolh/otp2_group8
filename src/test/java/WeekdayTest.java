
import com.automaatio.model.database.Weekday;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the Weekday class
 * @author Matleena Kankaanpää
 * 14.9.2023
 */

public class WeekdayTest {

    @Test
    public void testWeekdayConstructor() {

        Weekday weekday = new Weekday("Monday", "Maanantai", "Monday_ru", "Monday_ar");

        assertEquals(weekday.getName_en(), "Monday", "Name should be 'Monday'");
    }

    @Test
    public void testWeekdayGettersAndSetters() {
        Weekday weekday = new Weekday();
        weekday.setName_en("Friday");

        assertEquals(weekday.getName_en(), "Friday", "Name should be 'Friday'");
    }
}