import com.automaatio.model.database.EventTime;
import com.automaatio.model.database.Weekday;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Matkeela Kankaanpää
 *
 * The {@code EventTimeTest} class contains unit tests for the {@link EventTime} class.
 * It evaluates the functionality and behavior of {@code EventTime} objects, focusing on
 * their constructors and getter methods.
 *
 * This test class ensures that {@code EventTime} instances are correctly instantiated with
 * the provided start time, end time, and weekday, and that their properties are accurately
 * retrieved.
 */
public class EventTimeTest {

    /**
     * Tests the constructor and getter methods of the {@link EventTime} class.
     * It verifies that the {@code EventTime} object is correctly instantiated with the provided
     * start time, end time, and weekday, and that these properties are accurately retrieved.
     */
    @Test
    public void testEventTimeConstructorAndGetters() {
        Weekday weekday = new Weekday("Saturday", "lauantai", "суббота", "السبت");
        LocalDateTime startTime = LocalDateTime.of(2023,10,25,20,30);
        LocalDateTime endTime = LocalDateTime.of(2023,10,25,22,45);
        EventTime et = new EventTime(startTime, endTime, weekday);
        assertSame(et.getStartTime(), startTime);
        assertSame(et.getEndTime(), endTime);
        assertEquals(et.getWeekday().getName_en(), weekday.getName_en());
        assertEquals(et.getWeekday().getName_fi(), weekday.getName_fi());
        assertEquals(et.getWeekday().getName_ru(), weekday.getName_ru());
        assertEquals(et.getWeekday().getName_ar(), weekday.getName_ar());
    }
}
