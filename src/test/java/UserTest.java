import com.automaatio.model.database.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the User class
 *
 * @author Matleena Kankaanpää
 * @version 1.0 15.9.2023
 */

public class UserTest {

    /**
     * Tests the constructor
     */
    @Test
    public void testUserConstructor() {
        User user = new User("muumipeikko", "Muumi", "Peikko", "040-1234567", "muumi.peikko@muumilaakso.fi", "salaisuus", 30, 2);

        assertEquals("muumipeikko", user.getUsername(), "Username should be 'muumipeikko'");
        assertEquals("Muumi", user.getFirstName(), "First name should be 'Muumi'");
        assertEquals("Peikko", user.getLastName(), "Last name should be 'Peikko'");
        assertEquals("muumi.peikko@muumilaakso.fi", user.getEmail(), "Email should be 'muumi.peikko@muumilaakso.fi'");
        assertEquals("040-1234567", user.getPhoneNumber(), "Phone number should be '040-1234567'");
        assertEquals("salaisuus", user.getPassword(), "Password should be 'salaisuus'");
    }

    /**
     * Tests the getters and setters
     */
    @Test
    public void testUserGettersAndSetters() {
        User user = new User();

        user.setUsername("pikkumyy");
        user.setFirstName("Pikku");
        user.setLastName("Myy");
        user.setAge(20);
        user.setEmail("pikku.myy@muumilaakso.fi");
        user.setPhoneNumber("040-9876543");
        user.setPassword("secret");

        assertEquals("pikkumyy", user.getUsername(), "Username should be 'pikkumyy'");
        assertEquals("Pikku", user.getFirstName(), "First name should be 'Pikku'");
        assertEquals("Myy", user.getLastName(), "Last name should be 'Myy'");
        assertEquals("pikku.myy@muumilaakso.fi", user.getEmail(), "Email should be 'pikku.myy@muumilaakso.fi'");
        assertEquals("040-9876543", user.getPhoneNumber(), "Phone number should be '040-9876543'");
        assertEquals("secret", user.getPassword(), "Password should be 'secret'");
    }
}