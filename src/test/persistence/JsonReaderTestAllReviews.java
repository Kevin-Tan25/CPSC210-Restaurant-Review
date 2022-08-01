package persistence;

import model.RatedRestaurants;
import model.Restaurant;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Taken from JsonSerializationDemo file
class JsonReaderTestAllReviews extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderAllRestaurants reader = new JsonReaderAllRestaurants("./data/noSuchFile.json");
        try {
            RatedRestaurants restaurants = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReaderAllRestaurants reader = new JsonReaderAllRestaurants("./data/testReaderEmptyRestaurants.json");
        try {
            RatedRestaurants restaurants = reader.read();
            assertEquals(0, restaurants.getRatedRestaurants().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReaderAllRestaurants reader = new JsonReaderAllRestaurants("./data/testReaderGeneralRestaurants.json");
        try {
            RatedRestaurants ratedRestaurants = reader.read();
            List<Restaurant> restaurants = ratedRestaurants.getRatedRestaurants();
            assertEquals(2, restaurants.size());
            checkReview("McDonald's", "5728 University Blvd", 4, 3.99, "Good and cheap", restaurants.get(0).getReviews().get(0));
            checkReview("Miku", "4012 Robson St", 5, 46.78, "Best sushi in town", restaurants.get(1).getReviews().get(0));
            checkReview("Miku", "4012 Robson St", 5, 41.99, "Great service. Good ambiance.", restaurants.get(1).getReviews().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}