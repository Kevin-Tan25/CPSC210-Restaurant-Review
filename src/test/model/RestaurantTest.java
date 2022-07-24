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
        r2 = new Restaurant("McDonald's");
        ArrayList<String> list3= new ArrayList<String>(Arrays.asList("5728 University Blvd", "3308 W Broadway"));
        r3 = new Restaurant("McDonald's", list3);

        rev1 = new Review(1, r1, 4, 3.99, "Good and cheap");
        rev2 = new Review(2, r1, 2, 6.00, "Cheap but terrible service");
    }

    @Test
    public void testConstructor() {
        assertEquals(r3.getName(), "McDonald's");
        assertEquals(r3.getLocations().size(), 2);
        assertEquals(r3.getReviews().size(), 0);
        assertEquals(r3.getLocations().get(0), "5728 University Blvd");
        assertEquals(r3.getLocations().get(1), "3308 W Broadway");
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
    public void testAddLocation() {
        assertEquals(r2.getLocations().size(), 0);
        r2.addLocation("5728 University Blvd");
        assertEquals(r2.getLocations().size(), 1);
        assertEquals(r2.getLocations().get(0), "5728 University Blvd");
        r2.addLocation("3308 W Broadway");
        assertEquals(r2.getLocations().size(), 2);
        assertEquals(r2.getLocations().get(1), "3308 W Broadway");
    }

    @Test
    public void testEditLocation() {
        assertEquals(r3.getLocations().get(0), "5728 University Blvd");
        assertEquals(r3.getLocations().get(1), "3308 W Broadway");
        r3.editLocation(1, "2391 W 4th Ave");
        r3.editLocation(2, "5728 University Blvd");
        assertEquals(r3.getLocations().get(0), "2391 W 4th Ave");
        assertEquals(r3.getLocations().get(1), "5728 University Blvd");
    }

    @Test
    public void testDeleteLocation() {
        assertEquals(r3.getLocations().size(), 2);
        r3.deleteLocation(1);
        assertEquals(r3.getLocations().get(0), "3308 W Broadway");
        assertEquals(r3.getLocations().size(), 1);
        r3.deleteLocation(1);
        assertEquals(r3.getLocations().size(), 0);
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

}