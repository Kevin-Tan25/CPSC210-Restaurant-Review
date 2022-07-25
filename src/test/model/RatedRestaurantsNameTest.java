package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RatedRestaurantsNameTest {

    RatedRestaurantsName rr;

    @BeforeEach
    public void setUp() {
        rr = new RatedRestaurantsName();
    }

    @Test
    public void testConstructor() {
        assertEquals(rr.getRatedRestaurants().size(), 0);
    }

    @Test
    public void testAddAndDeleteRestaurant() {
        assertEquals(rr.getRatedRestaurants().size(), 0);
        rr.addRestaurant("McDonald's");
        assertEquals(rr.getRatedRestaurants().size(), 1);
        assertEquals(rr.getRatedRestaurants().get(0), "mcdonald's");
        rr.addRestaurant("Burger King");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(1), "burger king");
        rr.deleteRestaurant("McDonald's");
        assertEquals(rr.getRatedRestaurants().size(), 1);
        assertEquals(rr.getRatedRestaurants().get(0), "burger king");
        rr.deleteRestaurant("Burger King");
        assertEquals(rr.getRatedRestaurants().size(), 0);
    }

    @Test
    public void testEditRestaurantName() {
        assertEquals(rr.getRatedRestaurants().size(), 0);
        rr.addRestaurant("McDonald's");
        assertEquals(rr.getRatedRestaurants().size(), 1);
        assertEquals(rr.getRatedRestaurants().get(0), "mcdonald's");
        rr.addRestaurant("Burger King");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(1), "burger king");
        rr.editRestaurantName("McDonald's", "Five Guys");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(0), "five guys");
        rr.editRestaurantName("Burger King", "Chef Hung");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(1), "chef hung");
    }

    @Test
    public void testIsRatedRestaurant() {
        assertEquals(rr.getRatedRestaurants().size(), 0);
        rr.addRestaurant("McDonald's");
        assertEquals(rr.getRatedRestaurants().size(), 1);
        assertEquals(rr.getRatedRestaurants().get(0), "mcdonald's");
        rr.addRestaurant("Burger King");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertTrue(rr.isRatedRestaurant("Burger King"));
        assertFalse(rr.isRatedRestaurant("Five Guys"));
    }
}
