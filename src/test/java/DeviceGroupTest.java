import com.automaatio.model.database.User;
import com.automaatio.model.database.DeviceGroup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Elmo Erla
 *
 * The {@code DeviceGroupTest} class contains unit tests for the {@link DeviceGroup} class.
 * It evaluates the functionality and behavior of {@code DeviceGroup} objects, including
 * their constructors, getters, setters, and other methods.
 *
 * This test class ensures that {@code DeviceGroup} instances are correctly instantiated and
 * that their methods behave as expected, such as setting and retrieving properties.
 */
public class DeviceGroupTest {

    /**
     * Tests the constructor and getters of the {@link DeviceGroup} class.
     * It verifies that the {@code DeviceGroup} object is correctly instantiated with the provided name and user.
     */
    @Test
    public void testDeviceGroupConstructorAndGetters() {
        User user = new User();
        DeviceGroup deviceGroup = new DeviceGroup("TestGroup", user);

        assertEquals("TestGroup", deviceGroup.getName());
        assertEquals(user, deviceGroup.getUser());
    }

    /**
     * Tests the {@code setName} method of the {@link DeviceGroup} class.
     * It verifies that the name of the {@code DeviceGroup} object is correctly set and retrieved.
     */
    @Test
    public void testSetName() {
        DeviceGroup deviceGroup = new DeviceGroup();
        deviceGroup.setName("testName");

        assertEquals("testName", deviceGroup.getName());
    }

    /**
     * Tests the {@code toString} method of the {@link DeviceGroup} class.
     * It verifies that the {@code toString} method returns the correct string representation of the {@code DeviceGroup} object.
     */
    @Test
    public void testToString() {
        DeviceGroup deviceGroup = new DeviceGroup("TestGroup", null);

        assertEquals("TestGroup", deviceGroup.toString());
    }
}
