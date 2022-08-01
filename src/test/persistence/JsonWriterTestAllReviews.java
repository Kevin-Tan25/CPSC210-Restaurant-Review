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
class JsonWriterTestAllReviews extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            RatedRestaurants restaurants = new RatedRestaurants();
            JsonWriterUser writer = new JsonWriterUser("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUser() {
        try {
            RatedRestaurants restaurants = new RatedRestaurants();
            JsonWriterAllReviews writer = new JsonWriterAllReviews("./data/testWriterEmptyRestaurants.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReaderAllReviews reader = new JsonReaderAllReviews("./data/testWriterEmptyUser.json");
            restaurants = reader.read();
            assertEquals(0, restaurants.getRatedRestaurants().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            RatedRestaurants restaurants = new RatedRestaurants();
            Restaurant r1 = new Restaurant("Five Guys", "4823 Thompson Ave");
            r1.addReview(new Review(r1, 5, 8.45, "Lots of peanuts"));
            restaurants.addRestaurant(r1);
            Restaurant r2 = new Restaurant("A&W", "1498 Fisherman Dr");
            r2.addReview(new Review(r2, 4, 6.78, "Bomb poutine!"));
            restaurants.addRestaurant(r2);
            JsonWriterAllReviews writer = new JsonWriterAllReviews("./data/testWriterGeneralRestaurants.json.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReaderAllReviews reader = new JsonReaderAllReviews("./data/testWriterGeneralRestaurants.json");
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