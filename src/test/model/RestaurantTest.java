package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    Restaurant r1;

    @BeforeEach
    public void setUp() {
        r1 = new Restaurant("McDonald's", "5728 University Blvd");
    }

    @Test
    public void testConstructor() {
        assertEquals(r1.getName(), "McDonald's");
        assertEquals(r1.getLocations().size(), 1);
        assertEquals(r1.getReviews().size(), 0);
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
        assertEquals(r1.getLocations().size(), 1);
        assertEquals(r1.getLocations().get(0), "5728 University Blvd");
        r1.addLocation("3308 W Broadway");
        assertEquals(r1.getLocations().size(), 2);
        assertEquals(r1.getLocations().get(1), "3308 W Broadway");
        r1.addLocation("3308 W Broadway");
    }

}