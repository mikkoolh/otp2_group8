import com.automaatio.model.database.DeviceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test for the DeviceType class
 * @author Matleena Kankaanpää
 * 18.9.2023
 */

public class DeviceTypeTest {

    @Test
    public void testDeviceTypeConstructor() {
        DeviceType deviceType = new DeviceType("Lamp", "https://example.com/lamp.png");
        assertEquals("Lamp", deviceType.getDescription(), "Description should be 'Lamp'");
        assertEquals("https://example.com/lamp.png", deviceType.getImage(), "Description should be 'https://example.com/lamp.png'");
    }

    @Test
    public void testDeviceTypeGettersAndSetters() {
        DeviceType deviceType = new DeviceType();

        deviceType.setDescription("Fridge");
        deviceType.setImage("https://example.com/fridge.png");
        assertEquals("Fridge", deviceType.getDescription(), "Description should be 'Fridge'");
        assertEquals("https://example.com/fridge.png", deviceType.getImage(), "url should be 'https://example.com/fridge.png'");
    }
}
