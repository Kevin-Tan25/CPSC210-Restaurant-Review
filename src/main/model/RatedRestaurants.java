package model;

// A tandem Arraylist for ratedRestaurantsName, which keeps track of restaurants that have received reviews

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RatedRestaurants {

    private ArrayList<Restaurant> ratedRestaurants;
    private ArrayList<Restaurant> sortedRestaurant;
    private ArrayList<Restaurant> topFiveBestRatedRestaurants;

    // EFFECTS: creates an empty rated restaurants object
    public RatedRestaurants() {
        ratedRestaurants = new ArrayList<Restaurant>();
        sortedRestaurant = new ArrayList<Restaurant>();
        topFiveBestRatedRestaurants = new ArrayList<Restaurant>();
    }

    // REQUIRES: restaurant is not repeated in the list
    // MODIFIES: this
    // EFFECTS: adds the restaurant to the list
    public void addRestaurant(Restaurant restaurant) {
        ratedRestaurants.add(restaurant);
        sortedRestaurant.add(restaurant);
    }

    // REQUIRES: list is not empty, target restaurant exists in list
    // MODIFIES: this
    // EFFECTS: remove the restaurant from the list
    public void deleteRestaurant(String restaurant) {
        ratedRestaurants.remove(restaurant);
        sortedRestaurant.remove(restaurant);
    }

    // REQUIRES: restaurant is not repeated in the list, strings are not empty
    // MODIFIES: this
    // EFFECTS: changes the name of the target restaurant to new restaurant
    public void editRestaurantName(Restaurant targetRestaurant, Restaurant newRestaurant) {
        ratedRestaurants.set(ratedRestaurants.indexOf(targetRestaurant), newRestaurant);
        sortedRestaurant.set(sortedRestaurant.indexOf(targetRestaurant), newRestaurant);
    }

    // EFFECTS: returns true if ratedRestaurants already contains restaurant
    public Boolean isRatedRestaurant(Restaurant restaurant) {
        return ratedRestaurants.contains(restaurant);
    }

    public ArrayList<Restaurant> getRatedRestaurants() {
        return ratedRestaurants;
    }

    // Sorts the list in ascending order based on average rating
    // Referenced from Greg Anderson YouTube
    public ArrayList<Restaurant> getTopFiveRated() {
        Collections.sort(sortedRestaurant, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return Integer.compare(r2.getAverageRating(), r1.getAverageRating());
            }
        });
        if (sortedRestaurant.size() <= 5) {
            topFiveBestRatedRestaurants = sortedRestaurant;
        } else {
            // Takes top 5 restaurants
            for (int i = 0; i < 5; i++) {
                topFiveBestRatedRestaurants.add(sortedRestaurant.get(i));
            }
        }
        return topFiveBestRatedRestaurants;
    }
}
