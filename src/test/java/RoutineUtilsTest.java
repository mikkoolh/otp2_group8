import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.RoutineUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RoutineUtils class
 *
 * @author Nikita Nossenko
 * @version 1.0
 */

public class RoutineUtilsTest {
    private RoutineUtils routineUtils;
    private List<Routine> rutiinit;
    @Mock
    private CacheSingleton cache;
    @Mock
    private User mockUser;
    @Mock
    private Device mockDevice;
    @Mock
    private Feature mockFeature;

    /**
     * Setup actions to be run before each test.
     * Creates an ArrayList of test routines with mock users, devices and features.
     */
    @BeforeEach
    void setUp() {
        routineUtils = new RoutineUtils();
        rutiinit = new ArrayList<>();
        rutiinit.add(new Routine(mockUser, mockDevice, mockFeature, new EventTime(LocalDateTime.of(2023, 11, 24, 10, 30, 0),
                                                                                    LocalDateTime.of(2023, 11, 24, 11, 30, 0),
                                                                                        new Weekday()), true));
        rutiinit.add(new Routine(mockUser, mockDevice, mockFeature, new EventTime(LocalDateTime.of(2023, 11, 24, 14, 30, 0),
                LocalDateTime.of(2023, 11, 24, 17, 30, 0),
                new Weekday()), true));
        rutiinit.add(new Routine(mockUser, mockDevice, mockFeature, new EventTime(LocalDateTime.of(2023, 11, 22, 9, 25, 0),
                LocalDateTime.of(2023, 11, 23, 11, 20, 0),
                new Weekday()), true));
    }

    /**
     * Tests the sortByTime method of the RoutineUtils class.
     * The method is expected to sort a given list of routines by time.
     */
    @Test
    void testSortingByTime() {
        List<Routine> sortatutRutiinit = routineUtils.sortByTime(rutiinit);
        for (int i = 0; i < sortatutRutiinit.size() - 1; i++) {
            assertTrue(sortatutRutiinit.get(i).getEventTime().getStartTime().isBefore(sortatutRutiinit.get(i + 1).getEventTime().getStartTime()));
        }
    }

    /**
     * Tests the allAutomated method of the RoutineUtils class.
     * The method takes a list of routines as parameter and is expected
     * to return True if automation is enabled for each routine on the list.
     */
    @Test
    void testAutomaatio() {
        assertTrue(routineUtils.allAutomated(rutiinit));

        rutiinit.get(0).setAutomated(false);

        assertFalse(routineUtils.allAutomated(rutiinit));
    }

    /**
     * Tests the compareTimes method of the RoutineUtils class.
     * The method takes two LocalTime objects as parameters and is
     * expected to return True if the value of the first object is
     * earlier than that of the second.
     */
    @Test
    void testCompareTimes() {
        LocalTime startTime = LocalTime.of(22, 0);
        LocalTime endTime = LocalTime.of(23, 30);

        assertTrue(routineUtils.compareTimes(startTime, endTime));

        endTime = LocalTime.of(21, 15);

        assertFalse(routineUtils.compareTimes(startTime, endTime));
    }
}
