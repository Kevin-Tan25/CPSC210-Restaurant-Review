package persistence;

import model.Review;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUser.json");
        try {
            User user = reader.read();
            assertEquals("Test User", user.getUserName());
            assertEquals(0, user.getNumReviews());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals("Test User", user.getUserName());
            List<Review> reviews = user.getMyReviews();
            assertEquals(2, user.getNumReviews());
            checkReview("McDonald's", "5728 University Blvd", 4, 3.99, "Good and cheap", reviews.get(0));
            checkReview("Burger King", "1234 University Blvd", 3, 5.99, "Not bad", reviews.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}