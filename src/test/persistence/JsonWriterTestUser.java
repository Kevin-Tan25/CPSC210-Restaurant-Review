package persistence;


import model.Restaurant;
import model.Review;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Taken from JsonSerializationDemo file
class JsonWriterTestUser extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("Test User");
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
            User user = new User("Test User");
            JsonWriterUser writer = new JsonWriterUser("./data/testWriterEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReaderUser reader = new JsonReaderUser("./data/testWriterEmptyUser.json");
            user = reader.read();
            assertEquals("Test User", user.getUserName());
            assertEquals(0, user.getNumReviews());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("Test User");
            Restaurant r1 = new Restaurant("Five Guys", "4823 Thompson Ave");
            user.addReview(new Review(r1, 5, 8.45, "Lots of peanuts"));
            Restaurant r2 = new Restaurant("A&W", "1498 Fisherman Dr");
            user.addReview(new Review(r2, 4, 6.78, "Bomb poutine!"));
            JsonWriterUser writer = new JsonWriterUser("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReaderUser reader = new JsonReaderUser("./data/testWriterGeneralUser.json");
            user = reader.read();
            assertEquals("Test User", user.getUserName());
            List<Review> reviews = user.getMyReviews();
            assertEquals(2, user.getNumReviews());
            checkReview("Five Guys", "4823 Thompson Ave", 5, 8.45, "Lots of peanuts", reviews.get(0));
            checkReview("A&W", "1498 Fisherman Dr", 4, 6.78, "Bomb poutine!", reviews.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}