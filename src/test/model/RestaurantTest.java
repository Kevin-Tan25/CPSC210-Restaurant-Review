package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    Restaurant r1;
    Restaurant r2;
    Restaurant r3;
    Review rev1;
    Review rev2;

    @BeforeEach
    public void setUp() {
        r1 = new Restaurant("McDonald's", "5728 University Blvd");

        rev1 = new Review(r1, 4, 3.99, "Good and cheap");
        rev2 = new Review(r1, 2, 6.00, "Cheap but terrible service");
    }

    @Test
    public void testConstructor() {
        assertEquals(r1.getName(), "McDonald's");
        assertEquals(r1.getLocation(), "5728 University Blvd");
    }

    @Test
    public void testEditRestaurantName() {
        assertEquals(r1.getName(), "McDonald's");
        r1.editRestaurantName("McDonald");
        assertEquals(r1.getName(), "McDonald");
        r1.editRestaurantName("Burger King");
        assertEquals(r1.getName(), "Burger King");
    }

    @Test
    public void testAddReview() {
        assertEquals(r1.getReviews().size(),0);
        r1.addReview(rev1);
        assertEquals(r1.getReviews().size(),1);
        assertEquals(r1.getReviews().get(0), rev1);
        r1.addReview(rev2);
        assertEquals(r1.getReviews().size(),2);
        assertEquals(r1.getReviews().get(0), rev1);
        assertEquals(r1.getReviews().get(1), rev2);
    }

    @Test
    public void testGetName() {
        assertEquals(r1.getName(), "McDonald's");
    }

    @Test
    public void testToString() {
        assertEquals(r1.toString(), "[Restaurant Name = McDonald's]");
    }

    @Test
    public void testGetAverageCost() {
        r1.addReview(rev1);
        assertEquals(r1.getAverageCost(), 3.99);
        r1.addReview(rev2);
        assertEquals(r1.getAverageCost(), 4.995);
    }

    @Test
    public void testGetAverageRating() {
        r1.addReview(rev1);
        assertEquals(r1.getAverageRating(), 4);
        r1.addReview(rev2);
        assertEquals(r1.getAverageRating(), 3);
    }

    @Test
    public void testGetNumReview() {
        assertEquals(r1.getNumReviews(), 0);
        r1.addReview(rev1);
        assertEquals(r1.getNumReviews(), 1);
        r1.addReview(rev2);
        assertEquals(r1.getNumReviews(), 2);
    }
}