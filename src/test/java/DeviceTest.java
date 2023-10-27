import com.automaatio.model.database.Device;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    /*
    @Test
    public void testDeviceConstructor() {
        // luodaan ryhmä
      /*  DeviceGroup group1 = new DeviceGroup("Testiryhmä");

        // Luodaan laite
        Device device = new Device(100L, "TestiLaite", "Malli1", group1);

        // Tarkistetaan arvot
        assertTrue(device.isOnOff());
        assertTrue(device.isAutomation());
        assertEquals(100L, device.getUsageData());
        assertEquals("Name should be 'TestiLaite'", device.getName(), "TestiLaite");
        assertEquals("Model should be 'Malli1'", device.getModelCode(), "Malli1");
    }*/

    @Test
    public void testDeviceGettersAndSetters() {
        // Luodaan laite
        Device device = new Device();

        // Use setters to set the values
        device.switchOnOff();
        device.setUsageData(200L);
        device.setName("EriLaite");
        device.setModelCode("Malli2");

        // Use getters to retrieve the values and check them
        assertTrue(device.isOnOff());
        assertFalse(device.isAutomation());
        assertEquals(200L, device.getUsageData());
        assertEquals("EriLaite", device.getName(), "Name should be 'EriLaite'");
        assertEquals("Malli2", device.getModelCode(), "Model should be 'Malli2'");
    }
}
