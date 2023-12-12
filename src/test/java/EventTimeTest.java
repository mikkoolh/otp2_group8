import com.automaatio.model.database.EventTime;
import com.automaatio.model.database.Weekday;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the EventTime class
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */
public class EventTimeTest {

    /**
     * Tests the constructor, getters and setters
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
