package persistence;


import model.Review;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkReview(String restaurantName, String address, int rating, double cost, String comment, Review review ) {
        assertEquals(restaurantName, review.getRestaurant().getName());
        assertEquals(address, review.getRestaurant().getLocations());
        assertEquals(rating, review.getRating());
        assertEquals(cost, review.getCost());
        assertEquals(comment, review.getReviewComment());
    }
}
