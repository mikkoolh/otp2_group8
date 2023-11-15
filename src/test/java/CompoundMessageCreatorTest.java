import com.automaatio.utils.CompoundMessageCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompoundMessageCreatorTest {

    @Test
    void testCreate() {
        CompoundMessageCreator creator = new CompoundMessageCreator();

        String expectedMessage = "Testing create method with Java";
        String actualMessage = creator.create(new Object[]{"create", "Java"}, "testTemplate");

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCreateWithNoArguments() {
        CompoundMessageCreator creator = new CompoundMessageCreator();

        String expectedMessage = "No arguments provided";
        String actualMessage = creator.create(new Object[]{}, "noArgsTemplate");

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCreateWithMissingResourceKey() {
        CompoundMessageCreator creator = new CompoundMessageCreator();
        assertThrows(java.util.MissingResourceException.class, () -> {
            creator.create(new Object[]{"Java"}, "missingTemplate");
        });
    }
}
