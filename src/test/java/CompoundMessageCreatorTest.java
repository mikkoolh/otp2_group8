import com.automaatio.utils.CompoundMessageCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Elmo Erla
 *
 * The {@code CompoundMessageCreatorTest} class contains unit tests for the {@link CompoundMessageCreator} class.
 * It evaluates the functionality of creating compound messages based on provided templates and arguments.
 *
 * This test class ensures that the {@code CompoundMessageCreator} correctly constructs messages under various
 * scenarios, including standard operation, scenarios with no arguments, and cases with missing resource keys.
 */
class CompoundMessageCreatorTest {

    /**
     * Tests the {@code create} method for a standard scenario where arguments and a valid template key are provided.
     * It verifies that the created message matches the expected output.
     */
    @Test
    void testCreate() {
        CompoundMessageCreator creator = new CompoundMessageCreator();

        String expectedMessage = "Testing create method with Java";
        String actualMessage = creator.create(new Object[]{"create", "Java"}, "testTemplate");

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests the {@code create} method when no arguments are provided. It verifies that the created message
     * matches the expected output for scenarios where no arguments are given.
     */
    @Test
    void testCreateWithNoArguments() {
        CompoundMessageCreator creator = new CompoundMessageCreator();

        String expectedMessage = "No arguments provided";
        String actualMessage = creator.create(new Object[]{}, "noArgsTemplate");

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests the {@code create} method with a missing resource key. It verifies that a
     * {@link java.util.MissingResourceException} is thrown when a non-existent template key is used.
     */
    @Test
    void testCreateWithMissingResourceKey() {
        CompoundMessageCreator creator = new CompoundMessageCreator();
        assertThrows(java.util.MissingResourceException.class, () -> {
            creator.create(new Object[]{"Java"}, "missingTemplate");
        });
    }
}
