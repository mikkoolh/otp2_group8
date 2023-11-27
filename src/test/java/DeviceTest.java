import com.automaatio.model.database.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    private Device device;
    private DeviceGroup deviceGroup;

    @BeforeEach
    void setUp() {
        deviceGroup = new DeviceGroup("living room", new User());
        device = new Device(10, "device", "123", deviceGroup, "user");
    }

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

    @Test
    void testSwitchOnOff() {
        assertFalse(device.isOnOff());
        device.switchOnOff();
        assertTrue(device.isOnOff());
        device.switchOnOff();
        assertFalse(device.isOnOff());
    }

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
