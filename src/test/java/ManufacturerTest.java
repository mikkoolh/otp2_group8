import com.automaatio.model.database.Manufacturer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManufacturerTest {
    
    @Test
    public void testManufacturerConstructor() {
        //Luodaan uusi valmistaja
        Manufacturer manufacturer = new Manufacturer("Philips");

        assertEquals("Philips", manufacturer.getName(), "Manufacturer name should be 'Philips'");
    }

    @Test
    public void testManufacturerGettersAndSetters() {
        //Luodaan uusi valmistaja
        Manufacturer manufacturer = new Manufacturer();

        //Lisätään settereillä arvot
        manufacturer.setName("Ikea");

        //haetaan gettereillä tiedot ja tarkistetaan ne
        assertEquals("Ikea", manufacturer.getName(), "Manufacturers name should be 'Ikea'");
    }
}
