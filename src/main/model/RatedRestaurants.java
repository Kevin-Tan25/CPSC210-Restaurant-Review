package model;

// A tandem Arraylist for ratedRestaurantsName, which keeps track of restaurants that have received reviews

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RatedRestaurants {

    private ArrayList<Restaurant> ratedRestaurants;

    // EFFECTS: creates an empty rated restaurants object
    public RatedRestaurants() {
        ratedRestaurants = new ArrayList<Restaurant>();
    }

    // REQUIRES: restaurant is not repeated in the list
    // MODIFIES: this
    // EFFECTS: adds the restaurant to the list
    public void addRestaurant(Restaurant restaurant) {
        ratedRestaurants.add(restaurant);
    }

    // REQUIRES: list is not empty, target restaurant exists in list
    // MODIFIES: this
    // EFFECTS: remove the restaurant from the list
    public void deleteRestaurant(String restaurant) {
        ratedRestaurants.remove(restaurant);
    }

    // REQUIRES: restaurant is not repeated in the list, strings are not empty
    // MODIFIES: this
    // EFFECTS: changes the name of the target restaurant to new restaurant
    public void editRestaurantName(Restaurant targetRestaurant, Restaurant newRestaurant) {
        ratedRestaurants.set(ratedRestaurants.indexOf(targetRestaurant), newRestaurant);
    }

    // EFFECTS: returns true if ratedRestaurants already contains restaurant
    public Boolean isRatedRestaurant(Restaurant restaurant) {
        return ratedRestaurants.contains(restaurant);
    }

    public ArrayList<Restaurant> getRatedRestaurants() {
        return ratedRestaurants;
    }

    public Restaurant getTopFiveRated() {
        Collections.sort(ratedRestaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return r1.get().compareTo(o2.getAge());
            }
        });
    }
}
