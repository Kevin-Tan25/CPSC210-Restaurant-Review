package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatedRestaurantsTest {

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
        assertEquals(rr.getRatedRestaurants().get(0), "McDonald's");
        rr.addRestaurant("Burger King");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(1), "Burger King");
        rr.deleteRestaurant("McDonald's");
        assertEquals(rr.getRatedRestaurants().size(), 1);
        assertEquals(rr.getRatedRestaurants().get(0), "Burger King");
        rr.deleteRestaurant("Burger King");
        assertEquals(rr.getRatedRestaurants().size(), 0);
    }

    @Test
    public void testEditRestaurantName() {
        assertEquals(rr.getRatedRestaurants().size(), 0);
        rr.addRestaurant("McDonald's");
        assertEquals(rr.getRatedRestaurants().size(), 1);
        assertEquals(rr.getRatedRestaurants().get(0), "McDonald's");
        rr.addRestaurant("Burger King");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(1), "Burger King");
        rr.editRestaurantName("McDonald's", "Five Guys");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(0), "Five Guys");
        rr.editRestaurantName("Burger King", "Chef Hung");
        assertEquals(rr.getRatedRestaurants().size(), 2);
        assertEquals(rr.getRatedRestaurants().get(1), "Chef Hung");
    }
}
