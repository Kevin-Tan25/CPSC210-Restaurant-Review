package persistence;


import model.RatedRestaurants;
import model.Restaurant;
import model.Review;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Taken from JsonSerializationDemo file
class JsonWriterAllRestaurantsTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            RatedRestaurants restaurants = new RatedRestaurants();
            JsonWriterAllRestaurants writer = new JsonWriterAllRestaurants("./data/my\0IllegalFileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNoRestaurants() {
        try {
            RatedRestaurants restaurants = new RatedRestaurants();
            JsonWriterAllRestaurants writer = new JsonWriterAllRestaurants("./data/testWriterNoRestaurants.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReaderAllRestaurants reader = new JsonReaderAllRestaurants("./data/testWriterNoRestaurants.json");
            restaurants = reader.read();
            assertEquals(0, restaurants.getRatedRestaurants().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRestaurants() {
        try {
            RatedRestaurants restaurants = new RatedRestaurants();
            Restaurant r1 = new Restaurant("Five Guys", "4823 Thompson Ave");
            r1.addReview(new Review(r1, 5, 8.45, "Lots of peanuts"));
            restaurants.addRestaurant(r1);
            Restaurant r2 = new Restaurant("A&W", "1498 Fisherman Dr");
            r2.addReview(new Review(r2, 4, 6.78, "Bomb poutine!"));
            restaurants.addRestaurant(r2);
            JsonWriterAllRestaurants writer = new JsonWriterAllRestaurants("./data/testWriterGeneralRestaurants.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReaderAllRestaurants reader = new JsonReaderAllRestaurants("./data/testWriterGeneralRestaurants.json");
            restaurants = reader.read();
            List<Restaurant> numRestaurants = restaurants.getRatedRestaurants();
            assertEquals(2, numRestaurants.size());
            checkReview("Five Guys", "4823 Thompson Ave", 5, 8.45, "Lots of peanuts", numRestaurants.get(0).getReviews().get(0));
            checkReview("A&W", "1498 Fisherman Dr", 4, 6.78, "Bomb poutine!", numRestaurants.get(1).getReviews().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}