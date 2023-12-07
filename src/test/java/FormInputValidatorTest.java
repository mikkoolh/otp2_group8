import com.automaatio.utils.FormInputValidator;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mikko Hänninen
 *
 * The {@code FormInputValidatorTest} class contains unit tests for the {@link FormInputValidator} class.
 * It evaluates the functionality and behavior of input validation methods for various form fields.
 *
 * This test class ensures that the {@code FormInputValidator} correctly validates different types of input,
 * such as usernames, first names, last names, email addresses, phone numbers, and passwords, according to
 * specific validation rules.
 */
public class FormInputValidatorTest {
    private final FormInputValidator validator = new FormInputValidator();
    private final Text testField = new Text();

    /**
     * Tests the username validation method of the {@link FormInputValidator} class.
     * It verifies that the method correctly validates usernames based on specific rules.
     */
    @Test
    public void testUsernameValidation() {
        assertFalse(validator.validateUsername("", testField), "Username can't be blank");
        assertFalse(validator.validateUsername("thisusernameiswaywaywaywaywaywaywaytoolong", testField), "Username is too long");
        assertFalse(validator.validateUsername("bad username", testField), "Username can't contain spaces");
        assertTrue(validator.validateUsername("username", testField));
    }

    /**
     * Tests the first name validation method of the {@link FormInputValidator} class.
     * It verifies that the method correctly validates first names based on specific rules.
     */
    @Test
    public void testFirstNameValidation() {
        assertFalse(validator.validateFirstName("", testField), "First name can't be blank");
        assertFalse(validator.validateFirstName("Jugemu Jugemu Goko no Surikire Kaijarisuigyo no Suigyomatsu Unraimatsu Furaimatsu Ku Neru Tokoro ni Sumu Tokoro Yabura Koji no Bura Koji Paipo-paipo Paipo no Shuringan Shuringan no Gurindai Gurindai no Ponpokopi no Ponpokona no Chokyumei no Chosuke", testField), "First name is too long");
        assertTrue(validator.validateFirstName("Jane", testField));
    }

    /**
     * Tests the last name validation method of the {@link FormInputValidator} class.
     * It verifies that the method correctly validates last names based on specific rules.
     */
    @Test
    public void testLastNameValidation() {
        assertFalse(validator.validateLastName("", testField), "Last name can't be blank");
        assertFalse(validator.validateLastName("According to all known laws of aviation, there is no way a bee should be able to fly", testField), "Last name is too long");
        assertTrue(validator.validateLastName("Doe", testField));
    }

    /**
     * Tests the email validation method of the {@link FormInputValidator} class.
     * It verifies that the method correctly validates email addresses based on specific rules.
     */
    @Test
    public void testEmailValidation() {
        assertFalse(validator.validateEmail("", testField), "Email can't be blank");
        assertFalse(validator.validateEmail("strange@email@address.org.com.fi", testField), "Email format is invalid");
        assertTrue(validator.validateEmail("example@email.com", testField));
    }

    /**
     * Tests the phone number validation method of the {@link FormInputValidator} class.
     * It verifies that the method correctly validates phone numbers based on specific rules.
     */
    @Test
    public void testPhoneValidation() {
        assertFalse(validator.validatePhoneNumber("", testField), "Phone number can't be blank");
        assertFalse(validator.validatePhoneNumber("This isn't even a number", testField), "Phone number format is invalid");
        assertFalse(validator.validatePhoneNumber("111", testField), "Phone number is too short");
        assertFalse(validator.validatePhoneNumber("99999999999999999", testField), "Phone number is too long");
        assertTrue(validator.validatePhoneNumber("040-1234567", testField));
    }

    /**
     * Tests the password validation method of the {@link FormInputValidator} class.
     * It verifies that the method correctly validates passwords based on specific rules.
     */
    @Test
    public void testPasswordValidation() {
        assertFalse(validator.validatePassword("", testField), "Password can't be blank");
        assertFalse(validator.validatePassword("jjj", testField), "Password is too short");
        assertFalse(validator.validatePassword("thisisatotallybulletproofpassword123456789123456789123456789", testField), "Password is too long");
        assertFalse(validator.validatePassword("bad password", testField), "Password can't include spaces");
        assertFalse(validator.validatePassword("3019520290330", testField), "Password must contain at least one letter");
        assertFalse(validator.validatePassword("thispasswordhasonlyletters", testField), "Password must contain at least one number");
        assertTrue(validator.validatePassword("goodpassword123", testField));
    }
}
