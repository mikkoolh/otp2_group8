import com.automaatio.model.database.Feature;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FeatureTest {

    @Test
    public void testFeatureConstructor() {
        //luodaan feature
        Feature feature = new Feature(false, true, true, "himmennys", 10);

        //tarkkistetaan arvot
        assertFalse(feature.getAffectsOthers());
        assertTrue(feature.getIsActive());
        assertTrue(feature.getAdjustable());
        assertEquals( "himmennys", feature.getDescription(), "Description of feature should be 'himmennys'");
        assertEquals(10, feature.getTimesUsed());
    }

    @Test
    public void testFeatureGettersAndSetters() {
        Feature feature = new Feature();

        //syötetään arvot settereillä
        feature.setFeatureId(1);
        feature.setAffectsOthers(true);
        feature.setIsActive(false);
        feature.setAdjustable(false);
        feature.setDescription("äänen voimakkuus");
        feature.setTimesUsed(20);

        assertTrue(feature.getAffectsOthers());
        assertFalse(feature.getIsActive());
        assertFalse(feature.getAdjustable());
        assertEquals("äänen voimakkuus", feature.getDescription(), "Description should be 'äänen voimakkuus");
    }
}
