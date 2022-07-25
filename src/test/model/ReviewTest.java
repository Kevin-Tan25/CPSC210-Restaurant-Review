package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {

    Review rev1;

    Restaurant r1;

    @BeforeEach
    public void setUp() {
        r1 = new Restaurant("McDonald's", "5728 University Blvd");
        rev1 = new Review(r1, 4, 3.99, "Good and cheap");
    }

    @Test
    public void testConstructor() {
        assertEquals(rev1.getRestaurant(), r1);
        assertEquals(rev1.getRating(), 4);
        assertEquals(rev1.getCost(), 3.99);
        assertEquals(rev1.getReviewComment(), "Good and cheap");
    }

    @Test
    public void testChangeRating() {
        assertEquals(rev1.getRating(), 4);
        rev1.changeRating(3);
        assertEquals(rev1.getRating(),3);
        rev1.changeRating(2);
        assertEquals(rev1.getRating(),2);
    }

    @Test
    public void testChangeReview() {
        assertEquals(rev1.getReviewComment(), "Good and cheap");
        rev1.changeReview("Kinda mid");
        assertEquals(rev1.getReviewComment(), "Kinda mid");
        rev1.changeReview("Best burger ever");
        assertEquals(rev1.getReviewComment(), "Best burger ever");
    }

    @Test
    public void testChangeCost() {
        assertEquals(rev1.getCost(), 3.99);
        rev1.changeCost(4.50);
        assertEquals(rev1.getCost(), 4.50);
        rev1.changeCost(2.00);
        assertEquals(rev1.getCost(), 2.00);
    }
}
