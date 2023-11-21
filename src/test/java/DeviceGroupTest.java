import com.automaatio.model.database.User;
import com.automaatio.model.database.DeviceGroup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeviceGroupTest {
    @Test
    public void testDeviceGroupConstructorAndGetters() {
        User user = new User();
        DeviceGroup deviceGroup = new DeviceGroup("TestGroup", user);

        assertEquals("TestGroup", deviceGroup.getName());
        assertEquals(user, deviceGroup.getUser());
    }

    @Test
    public void testSetName() {
        DeviceGroup deviceGroup = new DeviceGroup();
        deviceGroup.setName("testName");

        assertEquals("testName", deviceGroup.getName());
    }

    @Test
    public void testToString() {
        DeviceGroup deviceGroup = new DeviceGroup("TestGroup", null);

        assertEquals("TestGroup", deviceGroup.toString());
    }
}
