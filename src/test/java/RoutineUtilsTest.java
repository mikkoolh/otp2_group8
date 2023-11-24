import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.LocalizationTool;
import com.automaatio.utils.RoutineUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testSortingByTime() {
        List<Routine> sortatutRutiinit = routineUtils.sortByTime(rutiinit);
        for (int i = 0; i < sortatutRutiinit.size() - 1; i++) {
            assertTrue(sortatutRutiinit.get(i).getEventTime().getStartTime().isBefore(sortatutRutiinit.get(i + 1).getEventTime().getStartTime()));
        }
    }

    @Test
    void testAutomaatio() {
        assertTrue(routineUtils.allAutomated(rutiinit));

        rutiinit.get(0).setAutomated(false);

        assertFalse(routineUtils.allAutomated(rutiinit));
    }

    @Test
    void testCompareTimes() {
        LocalTime startTime = LocalTime.of(22, 0);
        LocalTime endTime = LocalTime.of(23, 30);

        assertTrue(routineUtils.compareTimes(startTime, endTime));

        endTime = LocalTime.of(21, 15);

        assertFalse(routineUtils.compareTimes(startTime, endTime));
    }

    // TODO: Viikonpäivätesti
    /*@Test
    void testRoutinesByWeekday() {
        List<Weekday> weekdays = new ArrayList<>();
        weekdays.add(new Weekday("Mon", "ma", "пон", "الاثنين"));
        weekdays.add(new Weekday("Tue", "ti", "вт", "يوم الثلاثاء"));

        Map<String, ArrayList<Routine>> routinesByWeekday = routineUtils.getRoutinesByWeekday(weekdays, rutiinit);
        assertNotNull(routinesByWeekday);

        for (Weekday weekday : weekdays) {
            String localizedName = new LocalizationTool().localizeWeekday(weekday);
            assertTrue(routinesByWeekday.containsKey(localizedName));
        }

        for (Routine routine : rutiinit) {
            String localizedName = new LocalizationTool().localizeWeekday(routine.getEventTime().getWeekday());
            assertTrue(routinesByWeekday.get(localizedName).contains(routine));
        }
        Set<String> weekdayNames = routinesByWeekday.keySet();
    }*/

}
