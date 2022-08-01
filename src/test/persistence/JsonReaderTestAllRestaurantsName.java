package persistence;

import model.RatedRestaurantsName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Taken from JsonSerializationDemo file
public class JsonReaderTestAllRestaurantsName extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReaderAllRestaurantsName reader = new JsonReaderAllRestaurantsName("./data/noSuchFile.json");
        try {
            RatedRestaurantsName restaurantsName = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurantList() {
        JsonReaderAllRestaurantsName reader = new JsonReaderAllRestaurantsName("./data/testReaderNoRestaurants.json");
        try {
            RatedRestaurantsName restaurantsName = reader.read();
            assertEquals(restaurantsName.getRatedRestaurants().size(), 0);
        } catch (IOException e) {
            fail("IOException expected");
        }
    }

    @Test
    void testReaderGeneralRestaurantList() {
        JsonReaderAllRestaurantsName reader = new JsonReaderAllRestaurantsName("./data/testReaderGeneralRestaurants.json");
        try {
            RatedRestaurantsName restaurants = reader.read();
            List<String> listRestaurantName = restaurants.getRatedRestaurants();
            assertEquals(2, listRestaurantName.size());
            assertEquals("mcdonald's", listRestaurantName.get(0));
            assertEquals("miku", listRestaurantName.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
