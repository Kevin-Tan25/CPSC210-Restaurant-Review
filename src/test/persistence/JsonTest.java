package persistence;


import model.Review;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Taken from JsonSerializationDemo file
public class JsonTest {
    protected void checkReview(String restaurantName, String address, int rating, double cost, String comment, Review review ) {
        assertEquals(restaurantName, review.getRestaurant().getName());
        assertEquals(address, review.getRestaurant().getLocation());
        assertEquals(rating, review.getRating());
        assertEquals(cost, review.getCost());
        assertEquals(comment, review.getReviewComment());
    }
}
