import com.automaatio.model.database.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code DeviceTest} class contains unit tests for the {@link Device} class.
 * It evaluates the functionality and behavior of {@code Device} objects, including
 * their getters, setters, and other methods.
 *
 * This test class ensures that {@code Device} instances are correctly instantiated and
 * that their methods behave as expected, such as retrieving properties, toggling states,
 * and setting new values.
 *
 * @author Elmo Erla
 * @version 1.0
 */
public class DeviceTest {

    private Device device;
    private DeviceGroup deviceGroup;

    /**
     * Sets up the test environment before each test. This includes initializing a {@code DeviceGroup}
     * and a {@code Device} instance for testing.
     */
    @BeforeEach
    void setUp() {
        deviceGroup = new DeviceGroup("living room", new User());
        device = new Device(10, "device", "123", deviceGroup, "user");
    }

    /**
     * Tests the getter methods of the {@link Device} class.
     * It verifies that the properties of the {@code Device} object are correctly retrieved.
     */
    @Test
    void testDeviceGetters() {
        assertEquals(10, device.getUsageData());
        assertEquals("device", device.getName());
        assertEquals("123", device.getModelCode());
        assertEquals(deviceGroup, device.getDeviceGroup());
        assertEquals("user", device.getUserName());
        assertFalse(device.isOnOff());
        assertFalse(device.isAutomation());
    }

    /**
     * Tests the {@code switchOnOff} method of the {@link Device} class.
     * It verifies that the on/off state of the {@code Device} object is correctly toggled.
     */
    @Test
    void testSwitchOnOff() {
        assertFalse(device.isOnOff());
        device.switchOnOff();
        assertTrue(device.isOnOff());
        device.switchOnOff();
        assertFalse(device.isOnOff());
    }

    /**
     * Tests the setter methods of the {@link Device} class.
     * It verifies that the properties of the {@code Device} object are correctly set and retrieved.
     */
    @Test
    void testSetters() {
        device.setUsageData(20);
        assertEquals(20, device.getUsageData());

        device.setName("new device");
        assertEquals("new device", device.getName());

        device.setModelCode("234");
        assertEquals("234", device.getModelCode());

        DeviceGroup newGroup = new DeviceGroup("new room", new User());
        device.setDeviceGroup(newGroup);
        assertEquals(newGroup, device.getDeviceGroup());

        device.setAutomation(true);
        assertTrue(device.isAutomation());
    }
}
