package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatedRestaurantsTest {

    RatedRestaurants rr1;
    RatedRestaurants rr2;
    Restaurant r1;
    Restaurant r2;
    Restaurant r3;

    @BeforeEach
    public void setUp() {
        rr1 = new RatedRestaurants();
        rr2 = new RatedRestaurants();
        r1 = new Restaurant("McDonald's", "5728 University Blvd");
        r2 = new Restaurant("Burger King", "1234 University Blvd");
        r3 = new Restaurant("Five Guys", "4823 Thompson Ave");
        rr2.addRestaurant(r1);
        rr2.addRestaurant(r2);
    }

    @Test
    public void testConstructor() {
        assertEquals(rr2.getRatedRestaurants().size(), 2);
        assertEquals(rr2.getSortedRestaurant().size(), 2);
    }

    @Test
    public void testAddDeleteRestaurant() {
        assertEquals(rr1.getRatedRestaurants().size(),0);
        assertEquals(rr1.getSortedRestaurant().size(),0);
        rr1.addRestaurant(r1);
        assertEquals(rr1.getRatedRestaurants().size(),1);
        assertEquals(rr1.getRatedRestaurants().get(0), r1);
        assertEquals(rr1.getSortedRestaurant().size(),1);
        assertEquals(rr1.getSortedRestaurant().get(0), r1);
        rr1.addRestaurant(r2);
        assertEquals(rr1.getRatedRestaurants().size(),2);
        assertEquals(rr1.getRatedRestaurants().get(0), r1);
        assertEquals(rr1.getRatedRestaurants().get(1), r2);
        assertEquals(rr1.getSortedRestaurant().size(),2);
        assertEquals(rr1.getSortedRestaurant().get(0), r1);
        assertEquals(rr1.getSortedRestaurant().get(1), r2);
        rr1.deleteRestaurant(r1);
        assertEquals(rr1.getRatedRestaurants().size(),1);
        assertEquals(rr1.getRatedRestaurants().get(0), r2);
        assertEquals(rr1.getSortedRestaurant().size(),1);
        assertEquals(rr1.getSortedRestaurant().get(0), r2);
        rr1.deleteRestaurant(r2);
        assertEquals(rr1.getRatedRestaurants().size(),0);
        assertEquals(rr1.getSortedRestaurant().size(),0);
    }

    @Test
    public void testEditRestaurant() {
        assertEquals(rr2.getRatedRestaurants().size(),2);
        assertEquals(rr2.getSortedRestaurant().size(),2);
        rr2.editRestaurantName(r1, r3);
        assertEquals(rr2.getRatedRestaurants().size(),2);
        assertEquals(rr2.getRatedRestaurants().get(0), r3);
        assertEquals(rr2.getSortedRestaurant().size(),2);
        assertEquals(rr2.getSortedRestaurant().get(0), r3);
        rr2.editRestaurantName(r2, r1);
        assertEquals(rr2.getRatedRestaurants().size(),2);
        assertEquals(rr2.getRatedRestaurants().get(1), r1);
        assertEquals(rr2.getSortedRestaurant().size(),2);
        assertEquals(rr2.getSortedRestaurant().get(1), r1);
    }


}
