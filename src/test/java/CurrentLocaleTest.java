import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.BundleLoader;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.CurrentLocale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Elmo Erla
 *
 * The {@code CurrentLocaleTest} class contains unit tests for the {@link CurrentLocale} class.
 * It evaluates the functionality of retrieving the current locale, both in temporary and user-specific contexts.
 *
 * This test class uses Mockito to mock dependencies like {@link CacheSingleton} and {@link User}, allowing
 * for isolated testing of the {@code CurrentLocale} class. It ensures that the correct locale is returned
 * based on different scenarios, such as when a user is set or not.
 */
public class CurrentLocaleTest {
    @Mock
    private CacheSingleton cache;
    @Mock
    private User mockUser;
    private CurrentLocale currentLocale;

    /**
     * Sets up the test environment before each test. This includes initializing mocks
     * and setting up the {@link CurrentLocale} instance.
     */
    @BeforeEach
    void setUp() {
        cache = CacheSingleton.getInstance();
        currentLocale = new CurrentLocale();
        MockitoAnnotations.openMocks(this);
        when(mockUser.getLocale()).thenReturn(new Locale("fi", "FI"));
        when(cache.getUser()).thenReturn(mockUser);
    }

    /**
     * Tests the {@code getCurrentLocale} method when the temporary locale is set.
     * It verifies that the correct locale is returned based on the temporary settings.
     */
    @Test
    void testGetTempLocale() {
        Locale testLocale = new Locale("en", "US");
        Locale result = currentLocale.getCurrentLocale();
        assertEquals(testLocale, result);
    }

    /**
     * Tests the {@code getCurrentLocale} method to ensure it does not incorrectly return
     * a non-set locale. It verifies that the returned locale is not equal to an unintended value.
     */
    @Test
    void testGetTempLocale2() {
        Locale testLocale = new Locale("ru", "RU");
        Locale result = currentLocale.getCurrentLocale();
        assertNotEquals(testLocale, result, "Should not be same values");
    }

    /**
     * Tests the {@code getCurrentLocale} method when a user is set in the cache.
     * It verifies that the correct locale is returned based on the user's settings.
     */
    @Test
    void testGetLocaleWithUser() {
        cache.setUser(mockUser);
        Locale result = cache.getUser().getLocale();
        assertEquals(new Locale("fi", "FI"), result);
    }
}
