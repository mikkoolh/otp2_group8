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

public class CurrentLocaleTest {
    @Mock
    private CacheSingleton cache;
    @Mock
    private User mockUser;
    private CurrentLocale currentLocale;

    @BeforeEach
    void setUp() {
        cache = CacheSingleton.getInstance();
        currentLocale = new CurrentLocale();
        MockitoAnnotations.openMocks(this);
        when(mockUser.getLocale()).thenReturn(new Locale("fi", "FI"));
        when(cache.getUser()).thenReturn(mockUser);
    }

    @Test
    void testGetTempLocale() {
        Locale testLocale = new Locale("en", "US");
        Locale result = currentLocale.getCurrentLocale();
        assertEquals(testLocale, result);
    }

    @Test
    void testGetTempLocale2() {
        Locale testLocale = new Locale("ru", "RU");
        Locale result = currentLocale.getCurrentLocale();
        assertNotEquals(testLocale, result, "Should not be same values");
    }

    @Test
    void testGetLocaleWithUser() {
        cache.setUser(mockUser);
        Locale result = cache.getUser().getLocale();
        assertEquals(new Locale("fi", "FI"), result);
    }
}
